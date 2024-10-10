package org.example;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;

import java.util.Properties;
import java.util.function.Predicate;

/**
 * Hello world!
 *
 */
public class HelloStreams
{
    public static void main( String[] args )
    {
        /**
         * (Source processor) upstream: consume messages from a topic =>
         * process them :
         * (Sink processor) downstream produce to a topic
         */
        // 1. Stream configuration
        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "stream-id");
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092, localhost:9093");
//        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.Integer().getClass());
//        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        //2. Computational logic : Creating a topology : step by step computational logic of a stream processing app

        StreamsBuilder builder = new StreamsBuilder();

        // stream is always a consumer
        KStream<Integer, String> ks1= builder.stream("dummy",
                Consumed.with(Serdes.Integer(), Serdes.String()));

        ks1.foreach((k,v)-> System.out.println(k+" "+v));

       ks1.filter((k,v)-> v.startsWith("Odd"))
        .peek((k,v)-> System.out.println(k+" "+v))
                .to("odd", Produced.with(Serdes.Integer(), Serdes.String()));

        Topology topology = builder.build();

        // 3. open and start the stream
        KafkaStreams streams = new KafkaStreams(topology, properties);
        System.out.println("Starting streams");
        streams.start();
        System.out.println( "Hello World!" );
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("shutting down");
            streams.close();
        }));
    }
}
