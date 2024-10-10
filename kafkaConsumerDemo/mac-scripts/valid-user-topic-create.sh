#!/bin/bash

echo hey

KAFKA_HOME=/Users/Shalini/Desktop/kafkatraining/confluent-7.7.0

$KAFKA_HOME/bin/kafka-topics --create --bootstrap-server localhost:9092 --topic valid-user --partitions 3 --replication-factor 3
