package com.mbrunocesar.kafkaHandler.topicHandler;

import com.mbrunocesar.kafkaHandler.topicHandler.topic.TopicEntity;
import com.mbrunocesar.kafkaHandler.topicHandler.topic.TopicService;
import com.mbrunocesar.kafkaHandler.topicHandler.topic.TopicServiceImpl;
import org.apache.kafka.clients.admin.NewTopic;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class topicHandlerApplicationTests {

	TopicService topicService;

	public topicHandlerApplicationTests() {
		topicService = new TopicServiceImpl();
	}

	private void clearTestTopics(TopicEntity[] topics) {
		for (TopicEntity topic : topics) {
			if (topic.getName().startsWith("INTEGRATION_TEST_")) {
				topicService.delete(topic.getName());
			}
		}
	}

	private int countTestTopics(TopicEntity[] topics) {
		int countTestTopics = 0;
		for (TopicEntity topic : topics) {
			if (topic.getName().startsWith("INTEGRATION_TEST_")) {
				countTestTopics++;
			}
		}
		return countTestTopics;
	}

	private boolean isInTopicList(TopicEntity[] topics, String searchedTopicName) {
		for (TopicEntity topic : topics) {
			if (topic.getName().equals(searchedTopicName)) {
				return true;
			}
		}
		return false;
	}


	@Test
	void testBasicOperation() throws InterruptedException {
		TopicEntity[] topics = topicService.getAll();
		clearTestTopics(topics);

		Thread.sleep(100);
        topics = topicService.getAll();
		assertEquals(0, countTestTopics(topics));

		topicService.create(new TopicEntity("INTEGRATION_TEST_FirstTopic", 15, null));
		Thread.sleep(100);

		topicService.create(new TopicEntity("INTEGRATION_TEST_SecondTopic", 3, null));
		Thread.sleep(100);

		topicService.create(new TopicEntity("INTEGRATION_TEST_ThirdTopic", 4, null));

		Thread.sleep(100);
		topics = topicService.getAll();
		assertEquals(3, countTestTopics(topics));

		topicService.delete("INTEGRATION_TEST_SecondTopic");

		Thread.sleep(100);
		topics = topicService.getAll();
		assertFalse(isInTopicList(topics, "INTEGRATION_TEST_SecondTopic"));
		assertTrue(isInTopicList(topics, "INTEGRATION_TEST_FirstTopic"));

		Thread.sleep(100);
		topics = topicService.getAll();
		assertEquals(2, countTestTopics(topics));

		Thread.sleep(100);
		clearTestTopics(topics);
	}

}
