package com.mbrunocesar.kafkaHandler.kafkaManager.mocks;

import com.mbrunocesar.kafkaHandler.kafkaManager.topic.TopicEntity;
import com.mbrunocesar.kafkaHandler.kafkaManager.topic.TopicRepository;

import java.util.LinkedList;
import java.util.List;

public class TopicRepositoryMock extends TopicRepository {

    List<TopicEntity> listEntity;
    public TopicRepositoryMock() {
        listEntity = new LinkedList<TopicEntity>();
    }

    @Override
    public TopicEntity[] getAll() {
        return listEntity.toArray(new TopicEntity[0]);
    }

    @Override
    public TopicEntity create(TopicEntity newTopic) {
        listEntity.add(newTopic);

        return newTopic;
    }

    @Override
    public TopicEntity delete(String identifier) {
        listEntity.removeIf(topic -> topic.getName().equals(identifier));

        return null;
    }

}
