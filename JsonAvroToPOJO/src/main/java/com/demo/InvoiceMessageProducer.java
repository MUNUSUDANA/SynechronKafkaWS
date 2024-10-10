package com.demo;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
public class InvoiceMessageProducer {
    public static void main(String[] args) {
        System.out.println("Producer");
        System.out.println("Creating Kafka Producer...");
        Properties props = new Properties();
        props.put(ProducerConfig.CLIENT_ID_CONFIG, AppConfigs.applicationID);
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<Integer, String> producer = new KafkaProducer<>(props);

        System.out.println("Start sending messages...");
        System.out.println("sending");
        for (int i = 1; i <= 10; i++) {
            producer.send(new ProducerRecord<>(AppConfigs.topicName, i, "Hello " + i));
        }
        System.out.println("finished");
        System.out.println("Finished - Closing Kafka Producer.");
        producer.close();
    }
}
