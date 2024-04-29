package com.mbrunocesar.kafkaHandler.kafkaManager.cluster.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mbrunocesar.kafkaHandler.kafkaManager.topic.TopicEntity;

import java.io.Serializable;

public class NodeEntity implements Serializable {

    public NodeEntity(String id, String host, int port) {
        this.id = id;
        this.host = host;
        this.port = port;
    }

    @JsonProperty
    String id;

    @JsonProperty
    String host;

    @JsonProperty
    int port;

    @JsonProperty
    String[] topics;

    public void setTopics(TopicEntity[] topics) {
        this.topics = new String[topics.length];
        for (int i = 0; i < topics.length; i++) {
            this.topics[i] = topics[i].getName();
        }
    }
}
