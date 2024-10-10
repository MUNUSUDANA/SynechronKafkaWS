#!/bin/bash
CONFLUENT_HOME=/Users/Shalini/Documents/Citius_backup/jms/Kafka/installables/confluent-7.7.1
$CONFLUENT_HOME/bin/kafka-console-consumer --topic topic-test --bootstrap-server localhost:9092 --from-beginning
#--consumer-property max.partition.fetch.bytes=10485880