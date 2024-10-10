package com.demo;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DispatcherDemo {
    public static void main(String[] args) {

        Properties properties = new Properties();
        try {
            InputStream inputStream = new FileInputStream((AppConfigs.kafkaConfigFileLocation));
            properties.load(inputStream);
            properties.put(ProducerConfig.CLIENT_ID_CONFIG, AppConfigs.applicationID);
            properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
            properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        KafkaProducer<Integer, String> producer = new KafkaProducer<Integer, String>(properties);
        Thread[] threads = new Thread[AppConfigs.eventFiles.length];
        for (int i = 0; i < AppConfigs.eventFiles.length; i++) {
            threads[i] = new Thread(new Dispatcher(AppConfigs.eventFiles[i],AppConfigs.topicName,producer ));
            threads[i].start();

        }
        try{
            for (Thread thread: threads)
                thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
         producer.close();
        }
        System.out.println("finally done");
    }
}
