package com.example.firstservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/first-service")
public class FirstServiceController {

    private final Environment environment;

    public FirstServiceController(Environment environment) {
        this.environment = environment;
    }

    @GetMapping("/welcome")
    public String welcome(HttpServletRequest request) {
        log.info("my port is {}", request.getServerPort());
        return "welcome service1";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("first-request") String header) {
        log.info("header {}", header);
        return "hello message";
    }

    @GetMapping("/check")
    public String check(HttpServletRequest request) {
        log.info("my port is {}", request.getServerPort());
        return String.format("hello this is check from first service And My Port is %s", environment.getProperty("local.server.port"));
    }
}
