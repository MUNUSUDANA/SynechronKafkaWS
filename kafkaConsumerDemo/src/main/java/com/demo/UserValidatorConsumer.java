package com.demo;

import com.demo.types.User;
import io.confluent.kafka.serializers.KafkaJsonDeserializerConfig;
import io.confluent.kafka.serializers.json.KafkaJsonSchemaDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class UserValidatorConsumer {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.CLIENT_ID_CONFIG, AppConfigs.applicationID);
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
//        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "io.confluent.kafka.serializers.KafkaJsonDeserializer");
        properties.put(KafkaJsonDeserializerConfig.JSON_VALUE_TYPE, User.class);

        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,"io.confluent.kafka.serializers.json.KafkaJsonSchemaDeserializer");
        properties.put("schema.registry.url", "http://localhost:8081");
        // Kafka consumer groups
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "userValidatorGroup");
        // Kafka offsets and consumer positions
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        KafkaConsumer<Integer, User> consumer = new KafkaConsumer<Integer, User>(properties);
        consumer.subscribe(Arrays.asList("user-topic"));

        Properties props = new Properties();
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, AppConfigs.applicationID);
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                IntegerSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "io.confluent.kafka.serializers.KafkaJsonSerializer");

        Producer<Integer, User> producer = new KafkaProducer<>(props);
        System.out.println("consumer reading...");
        while (true) {
            ConsumerRecords<Integer, User> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<Integer, User> record : records) {
                if (record.value().getAge() > 20) {
                    producer.send(new ProducerRecord<Integer, User>(AppConfigs.validTopicName,
                            record.value().getUserId(), record.value()));
                } else {
                    producer.send(new ProducerRecord<Integer, User>(AppConfigs.invalidTopicName,
                            record.value().getUserId(), record.value()));
                }
            }
        }
    }

}
