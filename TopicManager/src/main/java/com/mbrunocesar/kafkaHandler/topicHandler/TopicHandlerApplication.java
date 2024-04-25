package com.mbrunocesar.kafkaHandler.topicHandler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class TopicHandlerApplication {

	@RequestMapping("/")
	public String healthCheck() {
		return "ok";
	}

	public static void main(String[] args) {
		SpringApplication.run(TopicHandlerApplication.class, args);
	}
}
