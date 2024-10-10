package com.demo;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * Hello world!
 *
 */
public class AppPartitionStrategy
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        Properties properties = new Properties();
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, AppConfigs.applicationID);
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());


        KafkaProducer<Integer, String> kafkaProducer = new KafkaProducer<>(properties);

        for(PartitionInfo info :kafkaProducer.partitionsFor("dummy")) {
            System.out.println(info.partition());
            System.out.println(info.toString());
        }

        System.out.println("Start producing messages");
        /**
         * 1. key => different value => hashing algorithm => DO NOT MODIFY THE PARTITION NUMBER
         * 2. key = constant
         * 3. key is null
         * <=2.3 ( round - robbin)
         * sticky partitioner
         */
        for (int i = 1; i <= 10 ; i++) {
            // timestamp => create time ( 0), log append time (1)
            kafkaProducer.send(new ProducerRecord<Integer, String>("dummy", "Hello " + i),
                    new Callback() {
                        @Override
                        public void onCompletion(RecordMetadata metadata, Exception exception) {
                            System.out.println(metadata.partition()+" "+metadata.offset());
                        }
                    }
            );
        }

        System.out.println("Sent messages");
        kafkaProducer.close();

    }
}
