#!/bin/bash

KAFKA_HOME=/Users/Shalini/Desktop/Kafka_OctWS/confluent-7.7.1

$KAFKA_HOME/bin/connect-distributed $KAFKA_HOME/etc/kafka/connect-distributed.properties
