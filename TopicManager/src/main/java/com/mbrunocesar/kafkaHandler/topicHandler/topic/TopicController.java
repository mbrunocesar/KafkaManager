package com.mbrunocesar.kafkaHandler.topicHandler.topic;

import org.springframework.web.bind.annotation.*;

@RestController
public class TopicController {

    TopicService topicService;

    @PostMapping("/")
    public TopicEntity createTopic(@RequestBody TopicEntity topic) {
        return topicService.create(topic);
    }

    @DeleteMapping("/:id")
    public TopicEntity deleteTopic(String id) {
        return topicService.delete(id);
    }

    @RequestMapping("/")
    public TopicEntity[] getAll() {
        return topicService.getAll();
    }
}
