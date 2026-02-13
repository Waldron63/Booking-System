package edu.co.bookingSystem.controller.health;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class NameControllerTest {

    @InjectMocks
    private NameController nameController;

    @Test
    void checkAPI() {
        String expectedResponse = "<h1>Hi, my name is Santiago Gualdron</h1>";
        String actualResponse = nameController.checkAPI();
        assertEquals(expectedResponse, actualResponse);
    }
}
