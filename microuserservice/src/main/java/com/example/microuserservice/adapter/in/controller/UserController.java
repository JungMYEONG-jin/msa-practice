package com.example.microuserservice.adapter.in.controller;

import com.example.microuserservice.adapter.out.ResponseUserMapper;
import com.example.microuserservice.data.RequestUser;
import com.example.microuserservice.data.ResponseUser;
import com.example.microuserservice.data.UserDto;
import com.example.microuserservice.port.in.UserService;
import com.example.microuserservice.prop.Greeting;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
//@RequestMapping("/user-service")
@RequiredArgsConstructor
public class UserController {
    private final Greeting greeting;
    private final Environment environment;
    private final UserService userService;
    private final ResponseUserMapper responseUserMapper;

    @GetMapping("/health-check")
    public String healthCheck(HttpServletRequest request) {
        return String.format("Working my port is %s, Local Address is %s, token secret is %s, token exp %s milliseconds",
                request.getServerPort(),
                request.getLocalAddr(),
                environment.getProperty("token.secret"),
                environment.getProperty("token.expiration.time"));
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
    public ResponseEntity createUser(@RequestBody RequestUser requestUser) {
        UserDto userDto = userService.createUser(requestUser);
        ResponseUser responseUser = responseUserMapper.dtoToResponse(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }

    @GetMapping("/users")
    public ResponseEntity getAllUser() {
        List<UserDto> all = userService.getAllUser();
        List<ResponseUser> result = new ArrayList<>();

        all.forEach(it -> {
            ResponseUser responseUser = responseUserMapper.dtoToResponse(it);
            responseUser.setOrders(new ArrayList<>());
            result.add(responseUser);
        });
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity getUser(@PathVariable("userId") String userId) {
        UserDto userByUserId = userService.getUserByUserId(userId);
        ResponseUser result = responseUserMapper.dtoToResponse(userByUserId);
//        result.setOrders(new ArrayList<>());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
