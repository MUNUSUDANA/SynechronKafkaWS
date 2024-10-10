package org.example;


import com.demo.invoice.types.PosInvoice;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.example.serdes.AppSerde;

import java.util.Properties;

public class PosFanoutApp {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, AppConfigs.applicationID);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);

        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, PosInvoice> KS0 = builder.stream(AppConfigs.posTopicName,
            Consumed.with(Serdes.String(), AppSerde.PosInvoice()));

        KS0.
                peek((k,v)-> System.out.println(v))
                .filter((k, v) ->
            v.getDeliveryType().equalsIgnoreCase(AppConfigs.DELIVERY_TYPE_HOME_DELIVERY))
            .to(AppConfigs.shipmentTopicName, Produced.with(Serdes.String(), AppSerde.PosInvoice()));

        KS0.filter((k, v) ->
            v.getCustomerType().equalsIgnoreCase(AppConfigs.CUSTOMER_TYPE_PRIME))
            .mapValues(invoice -> RecordBuilder.getNotification(invoice))
            .to(AppConfigs.notificationTopic, Produced.with(Serdes.String(), AppSerde.Notification()));

        KS0.mapValues(invoice -> RecordBuilder.getMaskedInvoice(invoice))
            .flatMapValues(invoice -> RecordBuilder.getHadoopRecords(invoice))
            .to(AppConfigs.hadoopTopic, Produced.with(Serdes.String(), AppSerde.HadoopRecord()));

        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        System.out.println("starting stream");
        streams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Stopping Stream");
            streams.close();
        }));

    }
}
