#!/bin/bash

KAFKA_HOME=/Users/Shalini/Documents/Citius_backup/jms/Kafka/installables/confluent-7.5.0

$KAFKA_HOME/bin/kafka-console-consumer --bootstrap-server localhost:9092 --topic nse-eod-topic --from-beginning

