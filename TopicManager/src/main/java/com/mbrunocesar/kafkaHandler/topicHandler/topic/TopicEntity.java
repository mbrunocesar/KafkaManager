package com.mbrunocesar.kafkaHandler.topicHandler.topic;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.PartitionInfo;

import java.io.Serializable;
import java.util.List;

public class TopicEntity implements Serializable {

    public TopicEntity(String name, int numPartitions, String additionalInfos) {
        this.name = name;
        this.numPartitions = numPartitions;
        this.additionalInfos = additionalInfos;
    }

    @JsonProperty
    String name;

    @JsonProperty
    int numPartitions;

    int replicationFactor = 1;

    @JsonProperty
    String additionalInfos;

    public String getName() {
        return name;
    }

    public NewTopic convertToKafkaEntity() {
        return new NewTopic(name, numPartitions, (short) replicationFactor);
    }

}