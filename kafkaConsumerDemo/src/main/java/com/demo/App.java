package com.demo;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.CLIENT_ID_CONFIG, AppConfigs.applicationID);
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        // which messages to read
//        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "g1");
        properties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 20);
        Consumer<Integer, String> consumer = new KafkaConsumer<>(properties);

        consumer.subscribe(Arrays.asList(AppConfigs.topicName));
        System.out.println("consuming");
        try {
            while (true) {

                ConsumerRecords<Integer, String> consumerRecords = consumer.poll(Duration.ofMillis(100));
                System.out.println("sending a poll req "+consumerRecords.count());
                for (ConsumerRecord<Integer, String> record : consumerRecords) {
                    System.out.println(record.value()+" "+record.partition());
                }

            }
        }finally {
            consumer.close();
        }


    }
}
