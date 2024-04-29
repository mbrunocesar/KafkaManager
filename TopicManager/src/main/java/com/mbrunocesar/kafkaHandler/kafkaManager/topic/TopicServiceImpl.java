package com.mbrunocesar.kafkaHandler.kafkaManager.topic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
        return topicRepository.getAll();
    }
}
