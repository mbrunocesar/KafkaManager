package com.mbrunocesar.kafkaHandler.kafkaManager.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class AuthEntity implements Serializable {

    @JsonProperty
    String email;

    public String getEmail() {
        return email;
    }
}