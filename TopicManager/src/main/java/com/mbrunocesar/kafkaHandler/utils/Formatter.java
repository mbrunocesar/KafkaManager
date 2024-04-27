package com.mbrunocesar.kafkaHandler.utils;

import com.mbrunocesar.kafkaHandler.topicHandler.topic.TopicEntity;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.common.PartitionInfo;

import java.util.List;
import java.util.Map;

public class Formatter {

    public static TopicEntity[] convertFromKafkaMapToArray(Map<String, List<PartitionInfo>> topicsMap) {
        TopicEntity[] topics = new TopicEntity[topicsMap.size()];

        int index = 0;
        for (List<PartitionInfo> partitionInfos : topicsMap.values()) {
            if (partitionInfos != null && !partitionInfos.isEmpty()) {
                topics[index] = new TopicEntity(
                        partitionInfos.getFirst().topic(),
                        partitionInfos.size(),
                        partitionInfos.toString()
                );
            }
            index++;
        }

        return topics;
    }

}
