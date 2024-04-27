package com.mbrunocesar.kafkaHandler.utils;

import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Properties;

public class KafkaConnector {

    Properties properties;

    public KafkaConnector() {
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

}
