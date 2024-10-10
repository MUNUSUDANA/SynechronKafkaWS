package com.demo;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Arrays;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class AppLargeMessageDemo
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        Properties properties = new Properties();
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, AppConfigs.applicationID);
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, "10485880");
        KafkaProducer<Integer, String> kafkaProducer = new KafkaProducer<>(properties);

        char[] chars = new char[1048576];
//        char[] chars = new char[1000];
        Arrays.fill(chars, 'a');
        String s=new String(chars);
       kafkaProducer.send(new ProducerRecord<>("dummy", 0, s), new Callback() {
           @Override
           public void onCompletion(RecordMetadata metadata, Exception exception) {
               System.out.println(metadata.topic());
               if(exception !=null)
                   System.out.println(exception.getMessage());
           }
       });


        System.out.println("Sent messages");
        kafkaProducer.close();

    }
}
