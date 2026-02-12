package edu.co.bookingSystem.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserTest {

    @Test
    void testAllArgsConstructor() {
        User user = new User("1", "John", "Doe", "john.doe@example.com", "password123");
        assertEquals("1", user.getId());
        assertEquals("John", user.getName());
        assertEquals("Doe", user.getLastName());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
    }

    @Test
    void testConstructorWithUserDto() {
        UserDto userDto = new UserDto("Jane", "Doe", "jane.doe@example.com", "password123");
        User user = new User(userDto);
        assertNull(user.getId());
        assertEquals("Jane", user.getName());
        assertEquals("Doe", user.getLastName());
        assertEquals("jane.doe@example.com", user.getEmail());
        assertNull(user.getPassword()); // Constructor doesn't set password from DTO
    }

    @Test
    void testSettersAndGetters() {
        User user = new User(null, null, null, null, null);
        user.setId("2");
        user.setName("Test");
        user.setLastName("User");
        user.setEmail("test.user@example.com");
        user.setPassword("secure");

        assertEquals("2", user.getId());
        assertEquals("Test", user.getName());
        assertEquals("User", user.getLastName());
        assertEquals("test.user@example.com", user.getEmail());
        assertEquals("secure", user.getPassword());
    }

    @Test
    void testUpdateFromDto() {
        User user = new User("1", "John", "Doe", "john.doe@example.com", "password123");
        UserDto userDto = new UserDto("John-Updated", "Doe-Updated", "john.doe.updated@example.com");

        user.update(userDto);

        assertEquals("1", user.getId());
        assertEquals("John-Updated", user.getName());
        assertEquals("Doe-Updated", user.getLastName());
        assertEquals("john.doe.updated@example.com", user.getEmail());
        assertEquals("password123", user.getPassword()); // Password should not be updated by this method
    }
}
