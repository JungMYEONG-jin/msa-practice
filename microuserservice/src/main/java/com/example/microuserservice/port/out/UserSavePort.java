package com.example.microuserservice.port.out;

import com.example.microuserservice.data.UserDto;
import com.example.microuserservice.persistence.entity.UserEntity;

public interface UserSavePort {
    UserEntity save(UserDto userDto);
}
