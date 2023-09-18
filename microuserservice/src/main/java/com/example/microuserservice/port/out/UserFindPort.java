package com.example.microuserservice.port.out;

import com.example.microuserservice.data.UserDto;

import java.util.List;

public interface UserFindPort {
    UserDto findUserByUserId(String userId);
    List<UserDto> findAll();
}
