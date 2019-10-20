using System.Threading.Tasks;
using System;
using FinHash.Impl;
using NLog;

namespace FinHash
{
    class Program
    {
        private static readonly Logger _logger = LogManager.GetCurrentClassLogger();
        static void Main(string[] args)
        {
            Kafka kafka = new Kafka();
            Task.Run(() => kafka.Consumer());
            Mongo mongo = new Mongo();
            AssetExpensesManagerImpl expensesManager = new AssetExpensesManagerImpl(mongo, kafka);
            Console.ReadLine();
        }
    }
}
