package com.mbrunocesar.kafkaHandler.topicHandler.auth;

import com.mbrunocesar.kafkaHandler.topicHandler.auth.dto.AuthEntity;
import com.mbrunocesar.kafkaHandler.topicHandler.auth.dto.AuthInput;
import com.mbrunocesar.kafkaHandler.topicHandler.auth.dto.AuthenticatedEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public AuthenticatedEntity login(@RequestBody AuthInput auth) {
        return this.authService.login(auth);
    }
}
