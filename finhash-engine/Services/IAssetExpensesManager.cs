using System.Collections.Generic;
using FinHash.Models;
using MongoDB.Bson;

namespace FinHash.Services
{
    public interface IAssetExpensesManager
    {
        Dictionary<(ObjectId, ObjectId), double> CalculateAmountPerRepresentativePerAsset(List<AssetRepresentative> representatives);
    }
}