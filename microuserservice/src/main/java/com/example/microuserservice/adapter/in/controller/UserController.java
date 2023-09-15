package com.example.microuserservice.adapter.in.controller;

import com.example.microuserservice.data.RequestUser;
import com.example.microuserservice.port.in.UserService;
import com.example.microuserservice.prop.Greeting;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-service")
@RequiredArgsConstructor
public class UserController {
    private final Greeting greeting;
    private final Environment environment;
    private final UserService userService;

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

    @PostMapping("/users")
    public String createUser(@RequestBody RequestUser requestUser) {
        userService.createUser(requestUser);
        return "User Registered...";
    }
}
