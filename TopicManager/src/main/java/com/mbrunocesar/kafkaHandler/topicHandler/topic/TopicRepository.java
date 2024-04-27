package com.mbrunocesar.kafkaHandler.topicHandler.topic;

import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class TopicRepository {

    Properties properties;

    public TopicRepository() {
        String kafka_endpoint = System.getenv("KAFKA_ENDPOINT");
        if (kafka_endpoint == null) {
            kafka_endpoint = "172.17.0.1:9092";
        }

        this.properties = new Properties();
        this.properties.put("bootstrap.servers", kafka_endpoint);
        this.properties.put("group.id", "api-consumer-group");
        this.properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        this.properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    }

    public KafkaConsumer<String, String> getConsumer() {
        return new KafkaConsumer<String, String>(this.properties);
    }

    public Admin getAdmin() {
        return Admin.create(this.properties);
    }

    public Map<String, List<PartitionInfo>> getAll() {
        KafkaConsumer<String, String> consumer = this.getConsumer();
        Map<String, List<PartitionInfo>> topicsMap = consumer.listTopics();
        consumer.close();

        return topicsMap;
    }
    public TopicEntity create(TopicEntity newTopic) {
        Admin kafkaAdmin = getAdmin();

        List<NewTopic> topics = new LinkedList<NewTopic>();
        topics.add(newTopic.convertToKafkaEntity());
        kafkaAdmin.createTopics(topics);

        return newTopic;
    }

    public TopicEntity delete(String identifier) {
        Admin kafkaAdmin = getAdmin();

        List<String> identifiers = new LinkedList<String>();
        identifiers.add(identifier);

        System.out.println(kafkaAdmin.deleteTopics(identifiers).all().toString());

        return null;
    }
}
