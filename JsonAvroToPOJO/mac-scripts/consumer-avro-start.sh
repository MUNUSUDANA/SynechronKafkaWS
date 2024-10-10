#!/bin/bash

KAFKA_HOME=/Users/Shalini/Desktop/Kafka_OctWS/confluent-7.7.1

$KAFKA_HOME/bin/kafka-console-consumer --bootstrap-server localhost:9092 --whitelist "emp-topic|cust-topic" --from-beginning

