package com.mbrunocesar.kafkaHandler.topicHandler.topic;

import com.mbrunocesar.kafkaHandler.utils.Formatter;
import org.apache.kafka.common.PartitionInfo;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

@Service
public class TopicServiceImpl implements TopicService {

    private static final Logger logger = LoggerFactory.getLogger(TopicServiceImpl.class);

    private final TopicRepository topicRepository;

    public TopicServiceImpl(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public TopicEntity create(TopicEntity newTopic) {
        logger.info("[TOPIC SERVICE] Call to Create() - " + newTopic.getName());
        return topicRepository.create(newTopic);
    }

    public TopicEntity delete(String identifier) {
        logger.info("[TOPIC SERVICE] Call to Delete() - " + identifier);
        return topicRepository.delete(identifier);
    }

    public TopicEntity[] getAll() {
        logger.info("[TOPIC SERVICE] Call to getAll()");
        Map<String, List<PartitionInfo>> topicsMap = topicRepository.getAll();

        return Formatter.convertFromKafkaMapToArray(topicsMap);
    }
}
