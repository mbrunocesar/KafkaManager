package com.mbrunocesar.kafkaHandler.kafkaManager.cluster;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClusterServiceTests {

	private final ClusterService clusterService;

	public ClusterServiceTests() {
		this.clusterService = new ClusterServiceImpl(new ClusterRepository());
	}


	@Test
	void testBasicOperation() throws InterruptedException {
		this.clusterService.getStatus();
	}

}
