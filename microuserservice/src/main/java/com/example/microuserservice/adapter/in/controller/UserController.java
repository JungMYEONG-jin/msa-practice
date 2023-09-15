package com.example.microuserservice.adapter.in.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserController {

    private final Environment environment;

    public UserController(Environment environment) {
        this.environment = environment;
    }

    @GetMapping("/health-check")
    public String healthCheck(HttpServletRequest request) {
        return String.format("Working my port is %s", request.getServerPort());
    }

    @GetMapping("/welcome")
    public String welcome() {
        return environment.getProperty("greeting.message");
    }
}
