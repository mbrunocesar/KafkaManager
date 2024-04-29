package com.mbrunocesar.kafkaHandler.kafkaManager.cluster;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ClusterServiceImpl implements ClusterService {

    private static final Logger logger = LoggerFactory.getLogger(ClusterServiceImpl.class);

    private final ClusterRepository clusterRepository;

    public ClusterServiceImpl(ClusterRepository clusterRepository) {
        this.clusterRepository = clusterRepository;
    }

    public ClusterEntity getStatus() {
        logger.info("[CLUSTER SERVICE] Call to getStatus()");
        return clusterRepository.getStatus();
    }
}
