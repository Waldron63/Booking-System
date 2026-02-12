package edu.co.bookingSystem.controller.auth;

import edu.co.bookingSystem.security.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthenticationControllerTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthenticationController authenticationController;

    private AuthRequestDto authRequestDto;
    private UserDetails userDetails;

    @BeforeEach
    void setUp() {
        authRequestDto = new AuthRequestDto("test@example.com", "password");
        userDetails = new User("test@example.com", "password", new ArrayList<>());
    }

    @Test
    void login() {
        String token = "test-token";
        when(userDetailsService.loadUserByUsername(authRequestDto.getEmail())).thenReturn(userDetails);
        when(jwtService.generateToken(userDetails)).thenReturn(token);

        ResponseEntity<AuthResponseDto> response = authenticationController.login(authRequestDto);

        assertNotNull(response.getBody());
        assertEquals(200, response.getStatusCode().value());
        assertEquals(token, response.getBody().getToken());

        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userDetailsService).loadUserByUsername(authRequestDto.getEmail());
        verify(jwtService).generateToken(userDetails);
    }
}
