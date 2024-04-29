package com.mbrunocesar.kafkaHandler.topicHandler.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenticatedEntity extends AuthEntity {

    public AuthenticatedEntity(String email, String token, boolean success) {
        this.email = email;
        this.token = token;
        this.success = success;
    }

    @JsonProperty
    String email;

    @JsonProperty
    String token;

    @JsonProperty
    boolean success;

    public String getToken() {
        return token;
    }

    public boolean isSuccess() {
        return success;
    }
}
