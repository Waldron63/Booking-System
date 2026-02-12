package edu.co.bookingSystem.controller.auth;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AuthRequestDtoTest {

    @Test
    void testNoArgsConstructor() {
        AuthRequestDto dto = new AuthRequestDto();
        assertNotNull(dto);
    }

    @Test
    void testAllArgsConstructor() {
        AuthRequestDto dto = new AuthRequestDto("test@example.com", "password");
        assertEquals("test@example.com", dto.getEmail());
        assertEquals("password", dto.getPassword());
    }

    @Test
    void testSettersAndGetters() {
        AuthRequestDto dto = new AuthRequestDto();
        dto.setEmail("test@example.com");
        dto.setPassword("password");

        assertEquals("test@example.com", dto.getEmail());
        assertEquals("password", dto.getPassword());
    }
}
