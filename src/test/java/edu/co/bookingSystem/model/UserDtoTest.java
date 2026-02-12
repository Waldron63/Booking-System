package edu.co.bookingSystem.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserDtoTest {

    @Test
    void testNoArgsConstructor() {
        UserDto userDto = new UserDto();
        assertNotNull(userDto);
        assertEquals("", userDto.getName());
        assertEquals("", userDto.getLastName());
        assertEquals("", userDto.getEmail());
        assertEquals("", userDto.getPassword());
    }

    @Test
    void testAllArgsConstructor() {
        UserDto userDto = new UserDto("John", "Doe", "john.doe@example.com", "password123");
        assertEquals("John", userDto.getName());
        assertEquals("Doe", userDto.getLastName());
        assertEquals("john.doe@example.com", userDto.getEmail());
        assertEquals("password123", userDto.getPassword());
    }

    @Test
    void testConstructorWithoutPassword() {
        UserDto userDto = new UserDto("Jane", "Doe", "jane.doe@example.com");
        assertEquals("Jane", userDto.getName());
        assertEquals("Doe", userDto.getLastName());
        assertEquals("jane.doe@example.com", userDto.getEmail());
        assertEquals("", userDto.getPassword());
    }

    @Test
    void testGetters() {
        UserDto userDto = new UserDto("Test", "User", "test.user@example.com", "secure");
        assertEquals("Test", userDto.getName());
        assertEquals("User", userDto.getLastName());
        assertEquals("test.user@example.com", userDto.getEmail());
        assertEquals("secure", userDto.getPassword());
    }
}