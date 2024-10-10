package com.demo;

import com.demo.avro.Employee;
import com.demo.types.User;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaJsonDeserializerConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class AvroConsumer {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.CLIENT_ID_CONFIG, AppConfigs.applicationID);
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,"io.confluent.kafka.serializers.KafkaAvroDeserializer");
        properties.put("schema.registry.url", "http://localhost:8081");
        // Kafka consumer groups
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "empGroup");
        // Kafka offsets and consumer positions
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        KafkaConsumer<String, Employee> consumer = new KafkaConsumer<String, Employee>(properties);
        consumer.subscribe(Arrays.asList("emp-topic"));

        try {
            while (true) {

                ConsumerRecords<String, Employee> consumerRecords = consumer.poll(Duration.ofMillis(100));
                System.out.println("sending a poll req "+consumerRecords.count());
                for (ConsumerRecord<String, Employee> record : consumerRecords) {
                    System.out.println(record.value()+" "+record.partition());
                }

            }
        }finally {
            consumer.close();
        }
    }
}
