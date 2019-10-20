using System;
using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;

namespace FinHash.Models
{
    [Serializable]
    [BsonIgnoreExtraElements]

    public class Asset
    {

        [BsonId]
        [BsonIgnoreIfDefault]
        public ObjectId Id { get; set; } // = ObjectId.GenerateNewId();
        public ulong Volume { get => _assetVolume; set => _assetVolume = value; }
        public double Factor { get => _factor; set => _factor = value; }
        public string Type { get => _type; set => _type = value; }
        public ulong ReferenceID { get => _referenceID; set => _referenceID = value; }
        public string Category { get => _category; set => _category = value; }
        public string Name { get => _name; set => _name = value; }

        private ulong _assetVolume;
        private double _factor;
        private string _type;
        private ulong _referenceID;
        private string _category;
        private string _name;

        public Asset(ulong Volume, string type, ulong referenceID, string category, string name)
        {
            this._assetVolume = Volume;
            this._factor = 1.0D;
            this._type = type;
            this._referenceID = referenceID;
            this._category = category;
            this._name = name;
        }

        public Asset(ulong Volume, double factor, string type, ulong referenceID, string category, string name)
        {
            this._assetVolume = Volume;
            this._factor = factor;
            this._type = type;
            this._referenceID = referenceID;
            this._category = category;
            this._name = name;
        }
    }
}