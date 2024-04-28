package com.mbrunocesar.kafkaHandler.topicHandler.topic;

import com.mbrunocesar.kafkaHandler.integrations.KafkaConnector;
import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TopicRepository {

    KafkaConnector kafkaConnector;

    public TopicRepository() {
        kafkaConnector = new KafkaConnector();
    }

    public Map<String, List<PartitionInfo>> getAll() {
        KafkaConsumer<String, String> consumer = this.kafkaConnector.getConsumer();
        Map<String, List<PartitionInfo>> topicsMap = consumer.listTopics();
        consumer.close();

        return topicsMap;
    }
    public TopicEntity create(TopicEntity newTopic) {
        Admin kafkaAdmin = this.kafkaConnector.getAdmin();

        List<NewTopic> topics = new LinkedList<NewTopic>();
        topics.add(newTopic.convertToKafkaEntity());
        kafkaAdmin.createTopics(topics);

        return newTopic;
    }

    public TopicEntity delete(String identifier) {
        Admin kafkaAdmin = this.kafkaConnector.getAdmin();

        List<String> identifiers = new LinkedList<String>();
        identifiers.add(identifier);

        kafkaAdmin.deleteTopics(identifiers);

        return null;
    }
}
