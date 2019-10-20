using System;
using System.Threading;
using System.Threading.Tasks;
using Confluent.Kafka;
using NLog;

namespace FinHash.Impl
{
    public class Kafka
    {
        private static readonly Logger _logger = LogManager.GetCurrentClassLogger();
        public delegate void CalculateCmdRecievedEventHandler(object source, EventArgs args);
        public event CalculateCmdRecievedEventHandler CmdReceived;

        public Kafka()
        {

        }

        public static async Task Producer(String str)
        {
            var config = new ProducerConfig { BootstrapServers = "10.255.255.214:9092" };
            using (var p = new ProducerBuilder<Null, string>(config).Build())
            {
                try
                {
                    var dr = await p.ProduceAsync("finhash-sub", new Message<Null, string> { Value = str });
                    _logger.Info($"Delivered '{dr.Value}' to '{dr.TopicPartitionOffset}'");
                }
                catch (ProduceException<Null, string> e)
                {
                    _logger.Info($"Delivery failed: {0}", e.Error.Reason);
                }
            }
        }

        public void Consumer()
        {
            var conf = new ConsumerConfig
            {
                GroupId = "test-consumer-group",
                BootstrapServers = "10.255.255.214:9092",
                AutoOffsetReset = AutoOffsetReset.Earliest
            };

            using (var c = new ConsumerBuilder<Ignore, string>(conf).Build())
            {
                c.Subscribe("finhash");

                CancellationTokenSource cts = new CancellationTokenSource();
                Console.CancelKeyPress += (_, e) =>
                {
                    e.Cancel = true; // prevent the process from terminating.
                    cts.Cancel();
                };

                try
                {
                    while (true)
                    {
                        try
                        {
                            var cr = c.Consume(cts.Token);
                            _logger.Info($"Consumed message '{cr.Value}' at: '{cr.TopicPartitionOffset}'.");
                            OnCmdReceived();
                        }
                        catch (ConsumeException e)
                        {
                            _logger.Info($"Error occured: {e.Error.Reason}");
                        }
                    }
                }
                catch (OperationCanceledException)
                {
                    // Ensure the consumer leaves the group cleanly and final offsets are committed.
                    c.Close();
                }
            }
        }

        protected virtual void OnCmdReceived()
        {
            if (CmdReceived != null)
                CmdReceived(this, EventArgs.Empty);
        }
    }
}