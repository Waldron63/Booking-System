package edu.co.bookingSystem.security;

import io.jsonwebtoken.ExpiredJwtException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    private JwtService jwtService;
    private UserDetails userDetails;
    private UserDetails anotherUserDetails;

    @BeforeEach
    void setUp() {
        // Using a Base64 encoded secret key as it would be in application.properties
        String secret = Base64.getEncoder().encodeToString("test-secret-key-for-jwt-service-testing-123456".getBytes());
        jwtService = new JwtService(secret);
        userDetails = new User("test@example.com", "password", new ArrayList<>());
        anotherUserDetails = new User("another@example.com", "password", new ArrayList<>());
    }

    @Test
    void generateToken() {
        String token = jwtService.generateToken(userDetails);
        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    void extractUsername() {
        String token = jwtService.generateToken(userDetails);
        String username = jwtService.extractUsername(token);
        assertEquals(userDetails.getUsername(), username);
    }

    @Test
    void extractExpiration() {
        String token = jwtService.generateToken(userDetails);
        Date expiration = jwtService.extractExpiration(token);
        assertTrue(expiration.after(new Date()));
    }

    @Test
    void validateToken_valid() {
        String token = jwtService.generateToken(userDetails);
        assertTrue(jwtService.validateToken(token, userDetails));
    }

    @Test
    void validateToken_invalidUser() {
        String token = jwtService.generateToken(userDetails);
        assertFalse(jwtService.validateToken(token, anotherUserDetails));
    }
}