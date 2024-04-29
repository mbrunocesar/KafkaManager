package com.mbrunocesar.kafkaHandler.kafkaManager.cluster;

import com.mbrunocesar.kafkaHandler.kafkaManager.cluster.dto.NodeEntity;
import com.mbrunocesar.kafkaHandler.kafkaManager.topic.TopicEntity;
import com.mbrunocesar.kafkaHandler.kafkaManager.topic.TopicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ClusterServiceImpl implements ClusterService {

    private static final Logger logger = LoggerFactory.getLogger(ClusterServiceImpl.class);

    private final ClusterRepository clusterRepository;
    private final TopicService topicService;

    public ClusterServiceImpl(ClusterRepository clusterRepository, TopicService topicService) {
        this.clusterRepository = clusterRepository;
        this.topicService = topicService;
    }

    private int countPartitions(TopicEntity[] topics) {
        int count = 0;
        for (TopicEntity topic : topics) {
            count += topic.getNumPartitions();
        }
        return count;
    }

    public ClusterEntity getStatus() {
        logger.info("[CLUSTER SERVICE] Call to getStatus()");

        ClusterEntity cluster = clusterRepository.getStatus();
        TopicEntity[] topics = topicService.getAll();

        cluster.setNumTopics(topics.length);
        cluster.setNumPartitions(countPartitions(topics));

        return cluster;
    }

    @Override
    public NodeEntity[] getNodes() {
        logger.info("[CLUSTER SERVICE] Call to getNodes()");
        NodeEntity[] nodes = clusterRepository.getNodes();

        TopicEntity[] topics = topicService.getAll();
        for (NodeEntity node : nodes) {
            node.setTopics(topics);
        }

        return nodes;
    }
}
