package edu.co.bookingSystem.controller.auth;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AuthResponseDtoTest {

    @Test
    void testNoArgsConstructor() {
        AuthResponseDto dto = new AuthResponseDto();
        assertNotNull(dto);
    }

    @Test
    void testAllArgsConstructor() {
        AuthResponseDto dto = new AuthResponseDto("test-token");
        assertEquals("test-token", dto.getToken());
    }

    @Test
    void testSetterAndGetter() {
        AuthResponseDto dto = new AuthResponseDto();
        dto.setToken("test-token");
        assertEquals("test-token", dto.getToken());
    }
}
