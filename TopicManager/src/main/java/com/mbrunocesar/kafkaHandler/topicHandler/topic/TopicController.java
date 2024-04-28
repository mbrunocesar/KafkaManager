package com.mbrunocesar.kafkaHandler.topicHandler.topic;

import org.springframework.web.bind.annotation.*;

@RestController
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }


    @PostMapping("/")
    public TopicEntity createTopic(@RequestBody TopicEntity topic) {
        return this.topicService.create(topic);
    }

    @DeleteMapping("/{name}")
    public TopicEntity deleteTopic(@PathVariable String name) {
        return this.topicService.delete(name);
    }

    @RequestMapping("/")
    public TopicEntity[] getAll() {
        return this.topicService.getAll();
    }
}
