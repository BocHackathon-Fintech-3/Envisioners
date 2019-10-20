using System.Linq;
using FinHash.Models;
using MongoDB.Driver;
using NLog;
using System;
using System.Collections.Generic;

namespace FinHash.Impl
{
    public class Mongo
    {
        private static readonly Logger _logger = LogManager.GetCurrentClassLogger();
        readonly MongoClient MongoClient;
        readonly IMongoDatabase MongoDatabase;
        readonly IMongoCollection<Asset> AssetsCollection;
        readonly IMongoCollection<AssetRepresentative> AssetRepresentativeCollection;
        readonly IMongoCollection<Dept> DeptsCollection;

        public Mongo()
        {
            MongoClient = new MongoClient("mongodb://10.255.255.214/finhash?w=majority");//192.168.10.46
            MongoDatabase = MongoClient.GetDatabase("finhash");
            AssetsCollection = MongoDatabase.GetCollection<Asset>("assets");
            AssetRepresentativeCollection = MongoDatabase.GetCollection<AssetRepresentative>("users");
            DeptsCollection = MongoDatabase.GetCollection<Dept>("depts");
        }

        public double GetTotalAmountDue()
        {
            double totalAmoundDue = 0D;
            int currentMonth = DateTime.Today.Month;
            int currentYear = DateTime.Today.Year;

            DateTime firstDayOfCurrentMonth = new DateTime(currentYear, currentMonth, 1, 0, 0, 0);

            var temp = DeptsCollection.Find<Dept>(dept => dept.Date >= firstDayOfCurrentMonth).ToList<Dept>(); ;

            foreach (Dept singleDept in temp)
                totalAmoundDue += singleDept.Amount;
            return totalAmoundDue;
        }

        public double GetTotalFactoredVolumeOfAssets()
        {
            double factoredVolume = 0D;
            var temp = AssetsCollection.Find<Asset>(asset => asset.Volume > 0);
            var temp2 = AssetsCollection.Find<Asset>(asset => asset.Factor > 0).ToList<Asset>();
            foreach (Asset factoredTotal in temp2)
                factoredVolume += (factoredTotal.Factor * factoredTotal.Volume);
            return factoredVolume;
        }


        public List<Asset> GetUserAsset(AssetRepresentative assetRepresentative)
        {
            var temp = AssetsCollection.Find<Asset>(asset => asset.Id != null).ToList<Asset>();
            return temp;
        }


        public List<AssetRepresentative> GetAllRepresentatives()
        {
            var temp = AssetRepresentativeCollection.Find<AssetRepresentative>(assetrepresentative => assetrepresentative.Id != null).ToList();
            List<AssetRepresentative> temp3 = new List<AssetRepresentative>();
            foreach (var temp2 in temp)
            {
                if (temp2.roles.First() == "ROLE_USER")
                {
                    temp3.Add(temp2);
                }
            }
            return temp3;
        }

        public Asset CreateAsset(Asset asset)
        {
            AssetsCollection.InsertOne(asset);
            _logger.Info("Asset added: assed id: {0}, asset volume: {1}, asset factor: {2}", asset.Id, asset.Volume, asset.Factor);
            return asset;
        }

        public Dept CreateDept(Dept dept)
        {
            DeptsCollection.InsertOne(dept);
            _logger.Info("Dept added: dept id: {0}, dept amount: {1}, dept date: {2}", dept.Id, dept.Amount, dept.Date);
            return dept;
        }
    }
}