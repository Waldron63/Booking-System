package edu.co.bookingSystem.security;

import edu.co.bookingSystem.model.User;
import edu.co.bookingSystem.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JwtUserDetailServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private JwtUserDetailService jwtUserDetailService;

    @Test
    void loadUserByUsername_whenUserExists() {
        String email = "test@example.com";
        User user = new User("1", "Test", "User", email, "password");
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        UserDetails userDetails = jwtUserDetailService.loadUserByUsername(email);

        assertEquals(user.getEmail(), userDetails.getUsername());
        assertEquals(user.getPassword(), userDetails.getPassword());
    }

    @Test
    void loadUserByUsername_whenUserDoesNotExist() {
        String email = "nonexistent@example.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> {
            jwtUserDetailService.loadUserByUsername(email);
        });
    }
}
