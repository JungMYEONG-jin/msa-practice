package com.example.microuserservice.port.in;

import com.example.microuserservice.data.RequestUser;
import com.example.microuserservice.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserServiceImpl userService;

    @Test
    void createUser() {
        RequestUser build = RequestUser.builder().name("test").pwd("aaaa").email("testmail").build();
        userService.createUser(build);
    }
}