package com.mbrunocesar.kafkaHandler.kafkaManager.topic;

import com.mbrunocesar.kafkaHandler.kafkaManager.auth.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topics")
public class TopicController {

    private final TopicService topicService;

    private final AuthService authService;

    public TopicController(TopicService topicService, AuthService authService) {
        this.topicService = topicService;
        this.authService = authService;
    }

    @PostMapping()
    public TopicEntity createTopic(
            @RequestHeader(name = "Authorization", required = false) String bearerToken,
            @RequestBody TopicEntity topic) throws Exception {
        authService.isValidToken(bearerToken);

        return this.topicService.create(topic);
    }

    @DeleteMapping("/{name}")
    public TopicEntity deleteTopic(
            @RequestHeader(name = "Authorization", required = false) String bearerToken,
            @PathVariable String name) throws Exception {
        authService.isValidToken(bearerToken);

        return this.topicService.delete(name);
    }

    @GetMapping()
    public TopicEntity[] getAll(
            @RequestHeader(name = "Authorization", required = false) String bearerToken) throws Exception {
        authService.isValidToken(bearerToken);

        return this.topicService.getAll();
    }
}
