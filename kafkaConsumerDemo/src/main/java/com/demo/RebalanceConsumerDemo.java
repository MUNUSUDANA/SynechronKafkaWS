package com.demo;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class RebalanceConsumerDemo
{
    public static void main( String[] args ) throws InterruptedException {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.CLIENT_ID_CONFIG, AppConfigs.applicationID);
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        // which messages to read
//        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "rebalance-group1");

        // we disable Auto Commit of offsets
        properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");

        KafkaConsumer<Integer, String> consumer = new KafkaConsumer<>(properties);

        ConsumerRebalanceListenerImpl rebalanceListener = new ConsumerRebalanceListenerImpl(consumer);
        final Thread mainThread = Thread.currentThread();

        // adding the shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.println("Detected a shutdown, let's exit by calling consumer.wakeup()...");
                consumer.wakeup();

                // join the main thread to allow the execution of the code in the main thread
                try {
                    mainThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        consumer.subscribe(Arrays.asList(AppConfigs.topicName), rebalanceListener);
        System.out.println("consuming");
        try {
            while (true) {
                ConsumerRecords<Integer, String> consumerRecords = consumer.poll(Duration.ofMillis(100));
                System.out.println("sending a poll req "+consumerRecords.count());
                for (ConsumerRecord<Integer, String> record : consumerRecords) {
                    System.out.println(record.value()+" "+record.partition());
                    rebalanceListener.addOffsetTotrack(record.topic(), record.partition(), record.offset());
                }
                consumer.commitAsync();

            }
        }finally {
            try {
                consumer.commitSync(rebalanceListener.getCurrentOffset()); // we must commit the offsets synchronously here
            } finally {
                consumer.close();
                System.out.println("The consumer is now gracefully closed.");
            }

        }


    }
}
