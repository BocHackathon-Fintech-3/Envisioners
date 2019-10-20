using System;
using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;


namespace FinHash.Models
{
    [Serializable]
    [BsonIgnoreExtraElements]
    public class Dept
    {
        [BsonId]
        [BsonIgnoreIfDefault]
        public ObjectId Id { set; get; } // = ObjectId.GenerateNewId();
        public double Amount { get => _amount; set => _amount = value; }
        public DateTime Date { get => _date; set => _date = value; }

        double _amount;
        DateTime _date;

        public Dept(double amount, DateTime date)
        {
            this._amount = amount;
            this._date = date;
        }
    }
}