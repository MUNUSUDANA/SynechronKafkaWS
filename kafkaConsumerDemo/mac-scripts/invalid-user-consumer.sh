#!/bin/bash

echo hey

KAFKA_HOME=/Users/Shalini/Desktop/kafkatraining/confluent-7.7.0

$KAFKA_HOME/bin/kafka-console-consumer --bootstrap-server localhost:9092 --topic invalid-user --from-beginning
