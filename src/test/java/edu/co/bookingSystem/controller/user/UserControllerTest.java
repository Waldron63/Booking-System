package edu.co.bookingSystem.controller.user;

import edu.co.bookingSystem.Exception.UserNotFoundException;
import edu.co.bookingSystem.model.User;
import edu.co.bookingSystem.model.UserDto;
import edu.co.bookingSystem.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService usersService;

    @InjectMocks
    private UserController userController;

    private User user;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        userDto = new UserDto("John", "Doe", "john.doe@example.com", "password");
        user = new User(userDto);
        user.setId("1");
    }

    @Test
    void createUser() {
        when(usersService.save(any(User.class))).thenReturn(user);

        ResponseEntity<User> response = userController.createUser(userDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(user, response.getBody());
        verify(usersService).save(any(User.class));
    }

    @Test
    void getAllUsers() {
        when(usersService.all()).thenReturn(Collections.singletonList(user));

        ResponseEntity<List<User>> response = userController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(user, response.getBody().get(0));
        verify(usersService).all();
    }

    @Test
    void findById_whenUserExists() {
        when(usersService.findById("1")).thenReturn(Optional.of(user));

        ResponseEntity<User> response = userController.findById("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
        verify(usersService).findById("1");
    }

    @Test
    void findById_whenUserNotFound() {
        when(usersService.findById("1")).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userController.findById("1"));
        verify(usersService).findById("1");
    }

    @Test
    void updateUser_whenUserExists() {
        UserDto updatedDto = new UserDto("Jane", "Doe", "jane.doe@example.com", "newpassword");
        when(usersService.findById("1")).thenReturn(Optional.of(user));
        when(usersService.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ResponseEntity<User> response = userController.updateUser("1", updatedDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Jane", response.getBody().getName());
        assertEquals("jane.doe@example.com", response.getBody().getEmail());
        verify(usersService).findById("1");
        verify(usersService).save(any(User.class));
    }

    @Test
    void updateUser_whenUserNotFound() {
        when(usersService.findById("1")).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userController.updateUser("1", userDto));
        verify(usersService).findById("1");
        verify(usersService, never()).save(any(User.class));
    }

    @Test
    void deleteUser_whenUserExists() {
        when(usersService.findById("1")).thenReturn(Optional.of(user));
        doNothing().when(usersService).deleteById("1");

        ResponseEntity<Void> response = userController.deleteUser("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(usersService).findById("1");
        verify(usersService).deleteById("1");
    }

    @Test
    void deleteUser_whenUserNotFound() {
        when(usersService.findById("1")).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userController.deleteUser("1"));
        verify(usersService).findById("1");
        verify(usersService, never()).deleteById("1");
    }
}
