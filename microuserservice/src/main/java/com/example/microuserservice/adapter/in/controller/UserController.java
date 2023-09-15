package com.example.microuserservice.adapter.in.controller;

import com.example.microuserservice.prop.Greeting;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-service")
@RequiredArgsConstructor
public class UserController {
    private final Greeting greeting;
    private final Environment environment;

    @GetMapping("/health-check")
    public String healthCheck(HttpServletRequest request) {
        return String.format("Working my port is %s", request.getServerPort());
    }

    @GetMapping("/greeting")
    public String greeting() {
        return String.format("Greeting my message is %s", greeting.getMessage());
    }

    @GetMapping("/welcome")
    public String welcome() {
        return environment.getProperty("greeting.message");
    }
}
