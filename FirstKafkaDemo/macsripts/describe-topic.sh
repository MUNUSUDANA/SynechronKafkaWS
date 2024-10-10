#!/bin/bash
CONFLUENT_HOME=/Users/Shalini/Desktop/Kafka_OctWS/confluent-7.7.1
#$CONFLUENT_HOME/bin/kafka-topics --describe  --bootstrap-server localhost:9092 --topic invoice
$CONFLUENT_HOME/bin/kafka-topics --describe  --bootstrap-server localhost:9092 --topic tester1
