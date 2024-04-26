package com.mbrunocesar.kafkaHandler.topicHandler;

import com.mbrunocesar.kafkaHandler.topicHandler.TopicHandlerApplication;
import com.mbrunocesar.kafkaHandler.topicHandler.topic.TopicController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class TopicHandlerApplicationTests {

	TopicController topicHandler;

	public TopicHandlerApplicationTests() {
		topicHandler = new TopicController();
	}

	void healthCheckShouldBeOk() {
        assertNotNull(topicHandler.getAll());
	}

}
