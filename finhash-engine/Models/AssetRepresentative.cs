using System;
using System.Collections.Generic;
using FinHash.Services;
using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;

namespace FinHash.Models
{
    [Serializable]
    [BsonIgnoreExtraElements]
    public class AssetRepresentative
    {
        [BsonId]
        [BsonIgnoreIfDefault]
        public ObjectId Id { set; get; } // = ObjectId.GenerateNewId();
        public string name { get; set; }
        public string username { get; set; }
        public string email { get; set; }
        public string password { get; set; }
        public string _class { get; set; }
        public List<string> roles { get; set; }
        public List<ObjectId> assets { get; set; }


        public AssetRepresentative(/*string name, ulong ID, List<Asset> representativeAssets*/)
        {
            // this._name = name;
            // this._ID = ID;
            // this._representativeAssets = representativeAssets;
        }
    }
}