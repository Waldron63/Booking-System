package edu.co.bookingSystem.service;

import edu.co.bookingSystem.model.User;
import edu.co.bookingSystem.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("1", "John", "Doe", "john.doe@example.com", "password");
    }

    @Test
    void save_newUser() {
        User newUser = new User(null, "Jane", "Doe", "jane.doe@example.com", "newpass");
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User u = invocation.getArgument(0);
            assertNotNull(u.getId());
            return u;
        });

        userService.save(newUser);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userCaptor.capture());
        assertNotNull(userCaptor.getValue().getId());
    }

    @Test
    void save_existingUser() {
        when(userRepository.save(user)).thenReturn(user);
        User savedUser = userService.save(user);
        assertEquals(user.getId(), savedUser.getId());
        verify(userRepository).save(user);
    }

    @Test
    void findById() {
        when(userRepository.findById("1")).thenReturn(Optional.of(user));
        Optional<User> foundUser = userService.findById("1");
        assertTrue(foundUser.isPresent());
        assertEquals(user, foundUser.get());
        verify(userRepository).findById("1");
    }

    @Test
    void all() {
        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));
        List<User> users = userService.all();
        assertEquals(1, users.size());
        assertEquals(user, users.get(0));
        verify(userRepository).findAll();
    }

    @Test
    void deleteById_whenUserExists() {
        when(userRepository.existsById("1")).thenReturn(true);
        doNothing().when(userRepository).deleteById("1");
        userService.deleteById("1");
        verify(userRepository).deleteById("1");
    }

    @Test
    void deleteById_whenUserDoesNotExist() {
        when(userRepository.existsById("1")).thenReturn(false);
        assertThrows(NoSuchElementException.class, () -> userService.deleteById("1"));
        verify(userRepository, never()).deleteById("1");
    }

    @Test
    void update_whenUserExists() {
        User updatedInfo = new User(null, "John Updated", "Doe Updated", "john.updated@example.com", "newpass");
        when(userRepository.existsById("1")).thenReturn(true);
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        User result = userService.update(updatedInfo, "1");

        assertEquals("1", result.getId());
        assertEquals(updatedInfo.getName(), result.getName());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void update_whenUserDoesNotExist() {
        User updatedInfo = new User(null, "John Updated", "Doe Updated", "john.updated@example.com", "newpass");
        when(userRepository.existsById("1")).thenReturn(false);
        assertThrows(NoSuchElementException.class, () -> userService.update(updatedInfo, "1"));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void findByEmail() {
        when(userRepository.findByEmail("john.doe@example.com")).thenReturn(Optional.of(user));
        Optional<User> foundUser = userService.findByEmail("john.doe@example.com");
        assertTrue(foundUser.isPresent());
        assertEquals(user, foundUser.get());
        verify(userRepository).findByEmail("john.doe@example.com");
    }
}
