package com.mbrunocesar.kafkaHandler.topicHandler.topic;

import com.mbrunocesar.kafkaHandler.utils.Formatter;
import org.apache.kafka.common.PartitionInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TopicServiceImpl implements TopicService {

    final TopicRepository topicRepository = new TopicRepository();

    public TopicEntity create(TopicEntity newTopic) {
        return topicRepository.create(newTopic);
    }

    public TopicEntity delete(String identifier) {
        return topicRepository.delete(identifier);
    }

    public TopicEntity[] getAll() {
        Map<String, List<PartitionInfo>> topicsMap = topicRepository.getAll();

        return Formatter.convertFromKafkaMapToArray(topicsMap);
    }
}
