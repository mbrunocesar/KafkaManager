package com.mbrunocesar.kafkaHandler.kafkaManager.topic;

public interface TopicService {
    public TopicEntity create(TopicEntity newTopic);

    public TopicEntity delete(String identifier);

    public TopicEntity[] getAll();
}
