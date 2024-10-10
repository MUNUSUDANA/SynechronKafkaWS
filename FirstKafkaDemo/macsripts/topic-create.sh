#!/bin/bash
CONFLUENT_HOME=/Users/Shalini/Desktop/Kafka_OctWS/confluent-7.7.1
#$CONFLUENT_HOME/bin/kafka-topics --create --bootstrap-server localhost:9092 --topic invoice --partitions 5 --replication-factor 3 --config segment.bytes=1000000

#$CONFLUENT_HOME/bin/kafka-topics --create --bootstrap-server localhost:9092 --topic tester1 --partitions 2 --replication-factor 3 --config min.insync.replicas=2


$CONFLUENT_HOME/bin/kafka-topics --create --bootstrap-server localhost:9092 --topic dummy --partitions 3 --replication-factor 3
#$CONFLUENT_HOME/bin/kafka-topics --alter --bootstrap-server localhost:9092 --topic dummy --partitions 5
#/Users/Shalini/Desktop/Kafka_OctWS/confluent-7.7.1/bin/kafka-run-class kafka.tools.DumpLogSegments --deep-iteration --print-data-log --files tmp/kafka-logs-0/invoice-0/00000000000000000000.log > log0.txt