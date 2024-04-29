package com.mbrunocesar.kafkaHandler.topicHandler.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthInput extends AuthEntity {

    public AuthInput(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @JsonProperty
    String email;

    @JsonProperty
    String password;

    @Override
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
