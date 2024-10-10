#!/bin/bash
CONFLUENT_HOME=/Users/Shalini/Desktop/Kafka_OctWS/confluent-7.7.1
$CONFLUENT_HOME/bin/zookeeper-server-start  $CONFLUENT_HOME/etc/kafka/zookeeper.properties

#
#set CONFLUENT_HOME=<PATH TO CONFLUENT>\confluent-7.7.0
#echo %CONFLUENT_HOME%
#%CONFLUENT_HOME%\bin\windows\zookeeper-server-start.bat %CONFLUENT_HOME %\etc\kafka\zookeeper.properties

