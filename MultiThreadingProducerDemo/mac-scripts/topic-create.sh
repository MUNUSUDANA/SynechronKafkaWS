#!/bin/bash

echo hey

KAFKA_HOME=/Users/Shalini/Documents/Citius_backup/jms/Kafka/installables/confluent-7.5.0

$KAFKA_HOME/bin/kafka-topics --create --bootstrap-server localhost:9092 --topic nse-eod-topic --partitions 5 --replication-factor 3
