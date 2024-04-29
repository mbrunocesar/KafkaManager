package com.mbrunocesar.kafkaHandler.kafkaManager.cluster;

import com.mbrunocesar.kafkaHandler.integrations.KafkaConnector;
import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.admin.DescribeClusterResult;
import org.apache.kafka.common.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.LinkedList;

@Repository
public class ClusterRepository {

    private static final Logger logger = LoggerFactory.getLogger(ClusterRepository.class);
    KafkaConnector kafkaConnector;

    public ClusterRepository() {
        kafkaConnector = new KafkaConnector();
    }

    public ClusterEntity getStatus() {
        Admin kafkaAdmin = this.kafkaConnector.getAdmin();

        DescribeClusterResult description = kafkaAdmin.describeCluster();
        Collection<Node> kafkaNodes = new LinkedList<Node>();

        try {
            kafkaNodes = description.nodes().get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        logger.warn(kafkaNodes.toString());

        for (Node node : kafkaNodes) {
            logger.warn(node.toString());
        }

        kafkaAdmin.close();


        return new ClusterEntity("ok");
    }

}
