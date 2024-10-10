package com.demo.datagenerator;

import com.demo.types.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.Random;

public class UserGenerator {
    private static final UserGenerator ourInstance = new UserGenerator();
    private final Random random;

    private User[] users;
    public static UserGenerator getInstance() {
        return ourInstance;
    }

    private UserGenerator() {
        //String DATAFILE = "/Users/Shalini/Documents/Citius_backup/jms/Kafka/KafkaWS/JsonAvroToPOJO/src/main/resources/data/users.json";
        URL url= getClass().getResource("/data/users.json");
        System.out.println(url.getPath());
        ObjectMapper mapper = new ObjectMapper();
        random = new Random();
        try {
            users = mapper.readValue(new File(url.getPath()), User[].class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int getUsersLength() {
        return users.length;
    }

    public User getNextUser(int index) {
        return users[index];
    }
}
