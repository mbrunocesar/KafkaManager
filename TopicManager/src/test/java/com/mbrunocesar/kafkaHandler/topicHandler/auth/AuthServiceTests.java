package com.mbrunocesar.kafkaHandler.topicHandler.auth;

import com.mbrunocesar.kafkaHandler.topicHandler.auth.dto.AuthInput;
import com.mbrunocesar.kafkaHandler.topicHandler.auth.dto.AuthenticatedEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class AuthServiceTests {

	private final AuthService authService;

	public AuthServiceTests() {
		this.authService = new AuthServiceImpl(new AuthRepository());
	}

	@Test
	void testLogin() throws InterruptedException {
		AuthenticatedEntity authenticatedWithSuccess = this.authService.login(new AuthInput("mbrunocesar@gmail.com", "123456"));
		assertTrue(authenticatedWithSuccess.isSuccess());

		AuthenticatedEntity authenticatedWithFailure = this.authService.login(new AuthInput("mbrunocesar@gmail.com", "123465"));
		assertFalse(authenticatedWithFailure.isSuccess());
	}

	@Test
	void testIsLoggedIn() throws InterruptedException {
		AuthenticatedEntity authenticatedWithSuccess = this.authService.login(new AuthInput("mbrunocesar@gmail.com", "123456"));
		assertTrue(this.authService.isLoggedIn(authenticatedWithSuccess));

		AuthenticatedEntity authenticatedWithFailure = this.authService.login(new AuthInput("mbrunocesar@gmail.com", "123465"));
		assertFalse(this.authService.isLoggedIn(authenticatedWithFailure));

		AuthenticatedEntity pseudoAuthenticated = new AuthenticatedEntity("mbrunocesar@gmail.com", "must_fail", true);
		assertFalse(this.authService.isLoggedIn(pseudoAuthenticated));
	}

}
