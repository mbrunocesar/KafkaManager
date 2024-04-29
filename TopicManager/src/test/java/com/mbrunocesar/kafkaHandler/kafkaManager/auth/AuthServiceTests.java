package com.mbrunocesar.kafkaHandler.kafkaManager.auth;

import com.mbrunocesar.kafkaHandler.kafkaManager.auth.dto.AuthInput;
import com.mbrunocesar.kafkaHandler.kafkaManager.auth.dto.AuthenticatedEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class AuthServiceTests {

	private final AuthService authService;

	public AuthServiceTests() {
		this.authService = new AuthServiceImpl(new AuthRepository());
	}

	@Test
	void testLogin() {
		AuthenticatedEntity authenticatedWithSuccess = this.authService.login(new AuthInput("mbrunocesar@gmail.com", "123456"));
		assertTrue(authenticatedWithSuccess.isSuccess());

		AuthenticatedEntity authenticatedWithFailure = this.authService.login(new AuthInput("mbrunocesar@gmail.com", "123465"));
		assertFalse(authenticatedWithFailure.isSuccess());
	}

	@Test
	void testIsLoggedIn() {
		AuthenticatedEntity authenticatedWithSuccess = this.authService.login(new AuthInput("mbrunocesar@gmail.com", "123456"));
		assertTrue(this.authService.isLoggedIn(authenticatedWithSuccess));

		AuthenticatedEntity authenticatedWithFailure = this.authService.login(new AuthInput("mbrunocesar@gmail.com", "123465"));
		assertFalse(this.authService.isLoggedIn(authenticatedWithFailure));

		AuthenticatedEntity pseudoAuthenticated = new AuthenticatedEntity("mbrunocesar@gmail.com", "must_fail", true);
		assertFalse(this.authService.isLoggedIn(pseudoAuthenticated));
	}

	@Test
	void testIsValidToken() {
		AuthenticatedEntity authenticatedWithSuccess = this.authService.login(new AuthInput("mbrunocesar@gmail.com", "123456"));
        try {
            assertTrue(this.authService.isValidToken(authenticatedWithSuccess.getToken()));
        } catch (Exception e) {
            System.err.println("NOT SHOULD HAPPEN");
        }

		AuthenticatedEntity authenticatedWithFailure = this.authService.login(new AuthInput("mbrunocesar@gmail.com", "123456"));
		try {
			this.authService.isValidToken(authenticatedWithFailure.getToken());
		} catch (Exception e) {
            assertNotNull(e.getMessage());
		}

		try {
			assertTrue(this.authService.isValidToken("Bearer bWJydW5vY2VzYXJAZ21haWwuY29t"));
		} catch (Exception e) {
			System.err.println("NOT SHOULD HAPPEN");
		}

		try {
			this.authService.isValidToken("any_thing_as_token");
		} catch (Exception e) {
			assertNotNull(e.getMessage());
		}
	}

}
