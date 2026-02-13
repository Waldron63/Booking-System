package edu.co.bookingSystem.controller.health;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/name")
public class NameController {

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String checkAPI() {
        return "<h1>Hi, my name is Santiago Gualdron</h1>";
    }
}
