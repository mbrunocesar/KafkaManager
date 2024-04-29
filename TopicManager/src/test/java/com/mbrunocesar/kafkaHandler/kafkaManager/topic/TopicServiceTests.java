package com.mbrunocesar.kafkaHandler.kafkaManager.topic;

import com.mbrunocesar.kafkaHandler.kafkaManager.mocks.TopicRepositoryMock;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TopicServiceTests {

	private final TopicService topicService;

	public TopicServiceTests() {
		this.topicService = new TopicServiceImpl(new TopicRepositoryMock());
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

		topicService.create(new TopicEntity("INTEGRATION_TEST_SecondTopic", 3, null));

		topicService.create(new TopicEntity("INTEGRATION_TEST_ThirdTopic", 4, null));

		topics = topicService.getAll();
		assertEquals(3, countTestTopics(topics));

		topicService.delete("INTEGRATION_TEST_SecondTopic");

		topics = topicService.getAll();
		assertFalse(isInTopicList(topics, "INTEGRATION_TEST_SecondTopic"));
		assertTrue(isInTopicList(topics, "INTEGRATION_TEST_FirstTopic"));

		topics = topicService.getAll();
		assertEquals(2, countTestTopics(topics));

		clearTestTopics(topics);
	}

}
