package com.mbrunocesar.kafkaHandler.kafkaManager.auth;

import com.mbrunocesar.kafkaHandler.kafkaManager.auth.dto.AuthInput;
import com.mbrunocesar.kafkaHandler.kafkaManager.auth.dto.AuthenticatedEntity;

public interface AuthService {
    public AuthenticatedEntity login(AuthInput auth);

    public boolean isLoggedIn(AuthenticatedEntity auth);

    public boolean isValidToken(String bearerToken) throws Exception;

}
