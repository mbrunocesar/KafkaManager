package com.mbrunocesar.kafkaHandler.kafkaManager.cluster;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClusterEntity {

    public ClusterEntity(String id) {
        this.id = id;
    }

    @JsonProperty
    String id;

}
