package com.mbrunocesar.kafkaHandler.topicHandler.auth;

import com.mbrunocesar.kafkaHandler.topicHandler.auth.dto.AuthInput;
import com.mbrunocesar.kafkaHandler.topicHandler.auth.dto.AuthenticatedEntity;
import org.springframework.stereotype.Repository;

@Repository
public class AuthRepository {

    private String masterPassword;
    private final JWT jwtProcessor;

    public AuthRepository() {
        this.masterPassword = System.getenv("AUTH_MASTER_PASSWORD");
        if (this.masterPassword == null) {
            this.masterPassword = "123456";
        }

        this.jwtProcessor = new JWT();
    }

    public AuthenticatedEntity login(AuthInput auth) {
        AuthenticatedEntity response;
        if (auth.getPassword().equals(masterPassword)) {
            String encodedToken = jwtProcessor.encode(auth.getEmail());
            response = new AuthenticatedEntity(auth.getEmail(), encodedToken, true);
        } else {
            response = new AuthenticatedEntity(auth.getEmail(), null, false);
        }

        return response;
    }

    public boolean isLoggedIn(String bearerToken) {
        return bearerToken!= null && bearerToken.startsWith("Bearer ");
    }

}
