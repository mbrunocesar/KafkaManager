package com.mbrunocesar.kafkaHandler;

import com.mbrunocesar.kafkaHandler.topicHandler.TopicHandlerApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TopicHandlerApplicationTests {

	TopicHandlerApplication topicHandler;

	public TopicHandlerApplicationTests() {
		topicHandler = new TopicHandlerApplication();
	}

	void healthCheckShouldBeOk() {
        assertEquals("ok", topicHandler.healthCheck());
	}

}
