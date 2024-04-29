package com.mbrunocesar.kafkaHandler.kafkaManager.mocks;

import com.mbrunocesar.kafkaHandler.kafkaManager.cluster.ClusterEntity;
import com.mbrunocesar.kafkaHandler.kafkaManager.cluster.ClusterRepository;
import com.mbrunocesar.kafkaHandler.kafkaManager.cluster.dto.NodeEntity;

import java.util.LinkedList;
import java.util.List;

public class ClusterRepositoryMock extends ClusterRepository {

    List<NodeEntity> listEntity;
    public ClusterRepositoryMock() {
        listEntity = new LinkedList<NodeEntity>();
        listEntity.add(new NodeEntity("1", "kafka", 9092));
        listEntity.add(new NodeEntity("2", "kafka.google.com", 443));
        listEntity.add(new NodeEntity("3", "localhost", 2027));
    }

    @Override
    public ClusterEntity getStatus() {
        return new ClusterEntity("1", "kafka", 9092);
    }

    @Override
    public NodeEntity[] getNodes() {
        return listEntity.toArray(new NodeEntity[0]);
    }

}
