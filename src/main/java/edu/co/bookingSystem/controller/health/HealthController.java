package edu.co.bookingSystem.controller.health;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/health")
public class HealthController {

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String checkAPI() {
        return "<h1>The API is working ok!</h1>";
    }
}
