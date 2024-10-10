package org.example;

import com.demo.invoice.types.Notification;
import com.demo.invoice.types.PosInvoice;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.StoreBuilder;
import org.apache.kafka.streams.state.Stores;
import org.example.serdes.AppSerde;

import java.util.Properties;

public class RewardsApplication {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "rewards-stream-id3");
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092, localhost:9093");
        properties.put(StreamsConfig.NUM_STREAM_THREADS_CONFIG, 3);

        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, PosInvoice> ks1 = builder.stream(AppConfigs.posTopicName,
                Consumed.with(Serdes.String(), AppSerde.PosInvoice()))
                .filter((k,v)->v.getCustomerType().equalsIgnoreCase(AppConfigs.CUSTOMER_TYPE_PRIME));

        StoreBuilder storeBuilder = Stores.keyValueStoreBuilder(
                Stores.inMemoryKeyValueStore(AppConfigs.REWARDS_STORE_NAME),
                Serdes.String(), Serdes.Double()
        );

        builder.addStateStore(storeBuilder);

        ks1.transformValues(()-> new RewardTransformer(), AppConfigs.REWARDS_STORE_NAME)
                .peek((k,v)-> System.out.println(v.getCustomerCardNo()+" "+v.getEarnedLoyaltyPoints()+" " +v.getTotalLoyaltyPoints()))

                .to(AppConfigs.rewardNotificationTopic, Produced.with(Serdes.String(), AppSerde.RewardNotification()));


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
