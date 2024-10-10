package com.demo;

import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.KafkaStorageException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ConsumerRebalanceListenerImpl implements ConsumerRebalanceListener {

    private KafkaConsumer<Integer, String> consumer ;

    private Map<TopicPartition, OffsetAndMetadata> currentOffset = new HashMap<>();

    public ConsumerRebalanceListenerImpl(KafkaConsumer<Integer, String> consumer){
        this.consumer = consumer;
    }

    public void addOffsetTotrack(String topic, int partition, long offset){
        currentOffset.put(
                new TopicPartition(topic, partition),
                new OffsetAndMetadata(offset+1,null));
    }

    public Map<TopicPartition, OffsetAndMetadata> getCurrentOffset() {
        return currentOffset;
    }

    @Override
    public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
        System.out.println("on partitions revoked callback");
        System.out.println(currentOffset);
        consumer.commitSync(currentOffset);
    }

    @Override
    public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
        System.out.println("on partitions assigned callback");
        partitions.forEach((topicPartition -> System.out.println(topicPartition.partition())));
    }

    @Override
    public void onPartitionsLost(Collection<TopicPartition> partitions) {
        ConsumerRebalanceListener.super.onPartitionsLost(partitions);
        System.out.println("on partitions lost callback");
    }
}
