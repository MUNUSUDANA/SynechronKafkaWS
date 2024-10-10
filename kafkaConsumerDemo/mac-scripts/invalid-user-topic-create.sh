#!/bin/bash

echo hey

KAFKA_HOME=/Users/Shalini/Desktop/kafkatraining/confluent-7.7.0

$KAFKA_HOME/bin/kafka-topics --create --bootstrap-server localhost:9092 --replication-factor 3 --partitions 3 --topic invalid-user
