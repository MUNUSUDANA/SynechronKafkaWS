package org.example;

import com.demo.types.User;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.example.serdes.AppSerde;

import java.util.Properties;

/**
 * Hello world!
 *
 */
public class UserStreams
{
    public static void main( String[] args )
    {
        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "stream-user-id");
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092, localhost:9093");
        StreamsBuilder builder = new StreamsBuilder();

        // stream is always a consumer
        KStream<Integer, User> ks1= builder.stream("user-topic",
                Consumed.with(Serdes.Integer(), AppSerde.User()));

       ks1.filter((k,v)-> v.getAge() >= 20)
               .peek((k,v)-> System.out.println("Valid "+v.getAge()+ " "+v.getUserName()))
               .to("user-valid-topic", Produced.with(Serdes.Integer(), AppSerde.User()));
        System.out.println("\ndiff streams\n");
        ks1.filter((k,v)-> v.getAge() < 20)
                .peek((k,v)-> System.out.println("Invalid "+v.getAge()+ " "+v.getUserName()))
                .map((k,v)-> new KeyValue<>(k, v.getUserName()))
                .peek((k,v)-> System.out.println("Invalid "+v))
                .to("user-invalid-name-topic", Produced.with(Serdes.Integer(), Serdes.String()));

        Topology topology = builder.build();

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
