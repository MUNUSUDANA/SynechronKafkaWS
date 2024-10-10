package com.demo;

//import com.demo.avro.Employee;
import com.demo.avro.Customer;
import com.demo.avro.Employee;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.errors.SerializationException;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class AvroProducer {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092, localhost:9094");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                org.apache.kafka.common.serialization.StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                io.confluent.kafka.serializers.KafkaAvroSerializer.class);
        props.put("schema.registry.url", "http://localhost:8081");
        //key.converter.schema.registry.url=http://localhost:8081
        //value.converter.schema.registry.url=http://localhost:8081
        props.put("key.converter.schema.registry.url","http://localhost:8081");
        props.put("value.converter.schema.registry.url","http://localhost:8081");
        KafkaProducer producer = new KafkaProducer(props);
       // try {
//            Schema schema = new Schema.Parser().parse(new File("src/main/resources/schema/avro/Employee.avsc"));
//            GenericRecord avroRecord = new GenericData.Record(schema);
//            avroRecord.put("firstName", "Shalini");
//            avroRecord.put("lastName", "Gupta");
//            avroRecord.put("age", 20);
//            avroRecord.put("phoneNumber", "232899821");
            Employee bob = Employee.newBuilder().setAge(25)
                .setFirstName("Prathamesh")
                .setLastName("Chaudhary")
                .setPhoneNumber("125-555-8787")
                .build();

            ProducerRecord<String, Employee> record = new ProducerRecord<>("emp-topic", "key1", bob);

//        Customer bob = Customer.newBuilder().setAge(25)
//                .setFirstName("shalini")
//                .setLastName("Mittal")
//               // .setCity("Mumbai")
//                .setPhoneNumber("125-555-8787")
//                .build();
//
//        ProducerRecord<String, Customer> record = new ProducerRecord<>("cust-topic", "key1", bob);
        System.out.println("sending");
            try {
              producer.send(record,((metadata, exception) -> {
                    System.out.println(exception);
                }));
             //   System.out.println(data.partition()+" "+data.topic());
            } catch(Exception e) {
                // may need to do something with it
                System.out.println("error");
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
// When you're finished producing records, you can flush the producer to ensure it has all been written to Kafka and
// then close the producer to free its resources.
            finally {
                System.out.println("DONE");
                producer.flush();
                producer.close();
            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }
}
