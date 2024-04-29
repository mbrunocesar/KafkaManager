package com.mbrunocesar.kafkaHandler.kafkaManager.cluster;

import com.mbrunocesar.kafkaHandler.kafkaManager.cluster.dto.NodeEntity;

public interface ClusterService {

    public ClusterEntity getStatus();

    public NodeEntity[] getNodes();
}
