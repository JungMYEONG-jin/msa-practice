package com.example.microuserservice.port.in;

import com.example.microuserservice.data.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);
}
