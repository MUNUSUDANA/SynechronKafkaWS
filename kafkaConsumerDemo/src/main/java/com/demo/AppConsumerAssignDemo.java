package com.demo;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class AppConsumerAssignDemo
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
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "group-assign");
        Consumer<Integer, String> consumer = new KafkaConsumer<>(properties);

        TopicPartition partition1 = new TopicPartition(AppConfigs.topicName, 0);
        TopicPartition partition2 = new TopicPartition(AppConfigs.topicName, 1);

        consumer.assign(Arrays.asList(partition1, partition2));

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

        System.out.println("consuming");
        try {
            while (true) {

                ConsumerRecords<Integer, String> consumerRecords = consumer.poll(Duration.ofMillis(100));
                System.out.println("sending a poll req "+consumerRecords.count());
                for (ConsumerRecord<Integer, String> record : consumerRecords) {
                    System.out.println(record.value()+" "+record.partition());
                }

            }
        }
        catch (WakeupException e) {
            System.out.println("Wake up exception!");
            // we ignore this as this is an expected exception when closing a consumer
        } catch (Exception e) {
            System.out.println("Unexpected exception " +e);
        }
        finally {

                consumer.close();
            }

    }
}
