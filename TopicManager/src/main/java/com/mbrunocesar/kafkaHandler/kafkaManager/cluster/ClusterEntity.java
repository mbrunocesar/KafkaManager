package com.mbrunocesar.kafkaHandler.kafkaManager.cluster;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClusterEntity {

    public ClusterEntity(String id, String host, int port) {
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
    int numTopics;

    @JsonProperty
    int numPartitions;

    public void setNumTopics(int numTopics) {
        this.numTopics = numTopics;
    }

    public void setNumPartitions(int numPartitions) {
        this.numPartitions = numPartitions;
    }
}
