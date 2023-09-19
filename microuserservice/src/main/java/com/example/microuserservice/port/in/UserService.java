package com.example.microuserservice.port.in;

import com.example.microuserservice.data.RequestUser;
import com.example.microuserservice.data.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    UserDto createUser(RequestUser requestUser);
    UserDto getUserByUserId(String userId);
    List<UserDto> getAllUser();
}
