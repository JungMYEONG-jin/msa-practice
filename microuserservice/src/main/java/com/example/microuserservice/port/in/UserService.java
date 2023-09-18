package com.example.microuserservice.port.in;

import com.example.microuserservice.data.RequestUser;
import com.example.microuserservice.data.ResponseUser;
import com.example.microuserservice.data.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(RequestUser requestUser);
    ResponseUser getUserByUserId(String userId);
    List<ResponseUser> getAllUser();
}
