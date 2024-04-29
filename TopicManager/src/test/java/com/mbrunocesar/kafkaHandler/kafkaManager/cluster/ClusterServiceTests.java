package com.mbrunocesar.kafkaHandler.kafkaManager.cluster;

import com.mbrunocesar.kafkaHandler.kafkaManager.mocks.ClusterRepositoryMock;
import com.mbrunocesar.kafkaHandler.kafkaManager.mocks.TopicRepositoryMock;
import com.mbrunocesar.kafkaHandler.kafkaManager.topic.TopicEntity;
import com.mbrunocesar.kafkaHandler.kafkaManager.topic.TopicService;
import com.mbrunocesar.kafkaHandler.kafkaManager.topic.TopicServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ClusterServiceTests {

	private final ClusterService clusterService;
	private final TopicService topicService;

	public ClusterServiceTests() {
		this.topicService = new TopicServiceImpl(new TopicRepositoryMock());
		this.clusterService = new ClusterServiceImpl(new ClusterRepositoryMock(), topicService);

		topicService.create(new TopicEntity("INTEGRATION_TEST_FirstTopic", 15, null));
		topicService.create(new TopicEntity("INTEGRATION_TEST_SecondTopic", 3, null));
		topicService.create(new TopicEntity("INTEGRATION_TEST_ThirdTopic", 4, null));
	}


	@Test
	void testBasicOperation() throws InterruptedException {
		ClusterEntity cluster = this.clusterService.getStatus();
		assertEquals(3, cluster.numTopics);
		assertEquals(22, cluster.numPartitions);
	}

}
