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
public class App 
{
    public static void main( String[] args ) throws ExecutionException, InterruptedException {
        System.out.println( "Hello World!" );

        Properties properties = new Properties();
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, AppConfigs.applicationID);
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
//        properties.put(ProducerConfig.ACKS_CONFIG, "all");// 0, 1,  all
//        properties.put(ProducerConfig.RETRIES_CONFIG, 2); // Integer.MAX_VALUE


        KafkaProducer<Integer, String> kafkaProducer = new KafkaProducer<>(properties);

        System.out.println("Start producing messages");
        for (int i = 1; i <= 10 ; i++) {
            System.out.println("sending");
            if(i%2==0)
                kafkaProducer.send(new ProducerRecord<Integer, String>("dummy", i, "Even " + i),
                        new Callback() {
                    @Override
                    public void onCompletion(RecordMetadata metadata, Exception exception) {
                        if(exception != null)
                            System.out.println(exception.getMessage());
                        System.out.println(metadata.toString());
                    }
                });
            else kafkaProducer.send(new ProducerRecord<Integer, String>("dummy", i, "Odd " + i),
                    new Callback() {
                        @Override
                        public void onCompletion(RecordMetadata metadata, Exception exception) {
                            if(exception != null)
                                System.out.println(exception.getMessage());
                            System.out.println(metadata);
                        }
                    });
//            RecordMetadata metadata = kafkaProducer.send(new ProducerRecord<Integer, String>("tester1", i, "hi " + i)).get();
//                       System.out.println(metadata.topic());

        }

        System.out.println("Sent messages");
        kafkaProducer.close();

    }
}
