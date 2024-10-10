package com.demo;

import com.demo.datagenerator.UserGenerator;
import com.demo.types.Product;
import com.demo.types.User;
import io.confluent.kafka.serializers.KafkaJsonSerializer;
import io.confluent.kafka.serializers.KafkaJsonSerializerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
// json, avro or protbuf
public class JSONProducer {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                IntegerSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                KafkaJsonSerializer.class);
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"io.confluent.kafka.serializers.json.KafkaJsonSchemaSerializer");
//        props.put("schema.registry.url", "http://localhost:8081");
//        props.put("key.converter.schema.registry.url","http://localhost:8081");
//        props.put("value.converter.schema.registry.url","http://localhost:8081");
        Producer<Integer, Object> producer = new KafkaProducer<>(props);
        UserGenerator generator = UserGenerator.getInstance();

        for(int i=0;i< generator.getUsersLength();i++){
            User user = generator.getNextUser(i);
            ProducerRecord<Integer, Object> record
                    = new ProducerRecord<>(AppConfigs.jsontopicname, user.getUserId(), user);
            System.out.println(producer.send(record).get());
        }
//        Product p1 = new Product();
//        p1.setpId(1);
//        p1.setProdName("pizza");
////        p1.setPrice(1231.3);
//        producer.send(new ProducerRecord<>(AppConfigs.jsontopicname, p1));
        producer.close();
    }
}
