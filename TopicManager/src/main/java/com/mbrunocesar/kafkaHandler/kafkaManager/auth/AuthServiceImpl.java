package com.mbrunocesar.kafkaHandler.kafkaManager.auth;

import com.mbrunocesar.kafkaHandler.kafkaManager.auth.dto.AuthInput;
import com.mbrunocesar.kafkaHandler.kafkaManager.auth.dto.AuthenticatedEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final AuthRepository authRepository;

    public AuthServiceImpl(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public AuthenticatedEntity login(AuthInput auth) {
        logger.info("[AUTH SERVICE] Call to Login() - " + auth.getEmail());
        AuthenticatedEntity result = authRepository.login(auth);
        logger.info("[AUTH SERVICE] Return from Login() - " + result.getToken());
        return result;
    }

    public boolean isLoggedIn(AuthenticatedEntity auth) {
        logger.info("[AUTH SERVICE] Call to isLoggedIn() - " + auth.getEmail());
        return authRepository.isLoggedIn(auth.getToken());
    }

    @Override
    public boolean isValidToken(String bearerToken) throws Exception {
        logger.info("[AUTH SERVICE] Call to isValidToken() - " + bearerToken);
        if (!authRepository.isLoggedIn(bearerToken)) {
            throw new Exception("Not Logged In");
        }

        return true;
    }
}
