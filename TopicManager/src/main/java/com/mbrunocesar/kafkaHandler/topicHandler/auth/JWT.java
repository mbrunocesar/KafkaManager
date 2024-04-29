package com.mbrunocesar.kafkaHandler.topicHandler.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Base64;

public class JWT {

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    // 36000 = 60 seconds * 60 minutes * 10 hours
    private static final long expirationTime = 36000;

    public String secretKey = System.getenv("JWT_SECRET_KEY");

    public JWT() {
        this.secretKey = System.getenv("JWT_SECRET_KEY_64_ENCODED");
        if (this.secretKey == null) {
            this.secretKey = "VEVTVF9TRUNSRVRfS0VZ";
        }
    }

    public String encode(String cypherData) {
        return "Bearer " + Base64.getEncoder().encodeToString(cypherData.getBytes());
    }

    public String decode(String cypherData) {
        return Base64.getDecoder().decode(cypherData).toString();
    }
}