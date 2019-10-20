using System;
using System.Collections.Generic;
using FinHash.Services;
using FinHash.Models;
using NLog;
using System.Linq;
using MongoDB.Bson;
using System.Threading.Tasks;
using Newtonsoft.Json;

namespace FinHash.Impl
{
    public class AssetExpensesManagerImpl : IAssetExpensesManager
    {
        private static readonly Logger _logger = LogManager.GetCurrentClassLogger();
        private static double _assetBasePrice;
        private double _totalFactoredVolumeOfAssets;
        private double _totalAmountDue;
        private Mongo _mongo;
        private Kafka _kafka;
   
        public AssetExpensesManagerImpl(Mongo mongo, Kafka kafka)
        {
            this._mongo = mongo;
            this._totalFactoredVolumeOfAssets = _mongo.GetTotalFactoredVolumeOfAssets();
            this._totalAmountDue = _mongo.GetTotalAmountDue();
            this._kafka = kafka;
    
            kafka.CmdReceived += this.OnCmdReceived;

            _logger.Info("Total amount due: {0}", _totalAmountDue);
            _logger.Info("_totalFactoredVolumeOfAssets = {0}", _totalFactoredVolumeOfAssets);
            CalculateBasePrice();
        }

        private void CalculateBasePrice() //For all assets
        {
            _assetBasePrice = _totalAmountDue / _totalFactoredVolumeOfAssets;
            _logger.Info("Asset Base Price = {0}", _assetBasePrice);
        }

        public Dictionary<(ObjectId, ObjectId), double> CalculateAmountPerRepresentativePerAsset(List<AssetRepresentative> representatives)
        {
            Dictionary<(ObjectId, ObjectId), double> pricePerRepresetativePerAsset = new Dictionary<(ObjectId, ObjectId), double>();
            foreach (var representative in representatives)
            {
                foreach (var asset in _mongo.GetUserAsset(representative))//representative.assets)
                {
                    pricePerRepresetativePerAsset.Add((representative.Id, asset.Id), CalculateAmountPerRepresentative(asset));
                }
            }
            return pricePerRepresetativePerAsset;
        }

        private double CalculateAmountPerRepresentative(Asset asset)
        {
            return _assetBasePrice * asset.Volume * asset.Factor;
        }

        public void OnCmdReceived(object source, EventArgs e)
        {
            _logger.Debug("CmdReceived");
             SentPrices(Calculate());
        }

        public void SentPrices(Dictionary<ObjectId, double> prices)
        {
            string str = JsonConvert.SerializeObject(prices);

            _logger.Info("{0}", str);

            Task.Run(() => Kafka.Producer(str));
        }

        public Dictionary<ObjectId, double> Calculate()
        {
            Dictionary<(ObjectId, ObjectId), double> amountPerUserPerAsset = CalculateAmountPerRepresentativePerAsset(_mongo.GetAllRepresentatives());
            var amountPerUser = GetUserAmount(amountPerUserPerAsset);
            return amountPerUser;
        }


        public Dictionary<ObjectId, double> GetUserAmount(Dictionary<(ObjectId, ObjectId), double> amountPerUser)
        {
            return amountPerUser.GroupBy(user => user.Key.Item1, kv => kv.Value).ToDictionary(kv => kv.Key, kv => kv.Sum());
        }
    }
}