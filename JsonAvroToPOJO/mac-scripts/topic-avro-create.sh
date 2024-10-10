#!/bin/bash

echo hey

KAFKA_HOME=/Users/Shalini/Desktop/Kafka_OctWS/confluent-7.7.1

$KAFKA_HOME/bin/kafka-topics --create --bootstrap-server localhost:9092 --replication-factor 3 --partitions 3 --topic emp-topic
$KAFKA_HOME/bin/kafka-topics --create --bootstrap-server localhost:9092 --replication-factor 3 --partitions 3 --topic cust-topic
