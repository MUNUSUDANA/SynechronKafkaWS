#!/bin/bash

KAFKA_HOME=/Users/Shalini/Desktop/kafkatraining/confluent-7.7.0

$KAFKA_HOME/bin/kafka-topics --describe  --bootstrap-server localhost:9092 --topic invoice

