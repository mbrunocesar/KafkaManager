package com.mbrunocesar.kafkaHandler.topicHandler.topic;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.kafka.clients.admin.NewTopic;

import java.io.Serializable;

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

    @JsonProperty
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