#!/bin/bash

KAFKA_HOME=/Users/Shalini/Desktop/Kafka_OctWS/confluent-7.7.1

$KAFKA_HOME/bin/kafka-console-consumer --bootstrap-server localhost:9092 --topic user-topic --from-beginning

/Users/Shalini/Desktop/Kafka_OctWS/confluent-7.7.1/bin/kafka-console-consumer --bootstrap-server localhost:9092 --topic user-valid-topic --from-beginning

/Users/Shalini/Desktop/Kafka_OctWS/confluent-7.7.1/bin/kafka-console-consumer --bootstrap-server localhost:9092 --topic user-invalid-name-topic --from-beginning