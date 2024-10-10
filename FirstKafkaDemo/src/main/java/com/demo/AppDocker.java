package com.demo;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * Hello world!
 *
 */
public class AppDocker
{
    public static void main( String[] args ) throws ExecutionException, InterruptedException {
        System.out.println( "Hello World!" );

        Properties properties = new Properties();
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "docker1");
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092, localhost:9093");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());


        KafkaProducer<Integer, String> kafkaProducer = new KafkaProducer<>(properties);

        System.out.println("Start producing messages");
       // for (int i = 1; i <= 10 ; i++) {
            kafkaProducer.send(new ProducerRecord<Integer, String>("dummy-topic", 1, "Odd " + 1),
                    new Callback() {
                        @Override
                        public void onCompletion(RecordMetadata metadata, Exception exception) {
                            if(exception != null)
                                System.out.println(exception.getMessage());
                            System.out.println(metadata);
                        }
                    });
      //  }

        System.out.println("Sent messages");
        kafkaProducer.close();

    }
}
