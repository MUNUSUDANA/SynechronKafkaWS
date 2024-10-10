package com.demo;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Dispatcher implements   Runnable{
    private String fileLocation;
    private String topicName;
    private KafkaProducer<Integer, String> producer;

    public Dispatcher(String fileLocation, String topicName, KafkaProducer<Integer, String> producer) {
        this.fileLocation = fileLocation;
        this.topicName = topicName;
        this.producer = producer;
    }

    @Override
    public void run() {
        // reads data from given file
        File file = new File(fileLocation);
        System.out.println("start sending");
        int counter = 0;
        try(Scanner sc = new Scanner(file)){
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                producer.send(new ProducerRecord<Integer, String>(topicName, null, line));
                counter++;
            }
            System.out.println("finished sending "+counter+" messages");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
