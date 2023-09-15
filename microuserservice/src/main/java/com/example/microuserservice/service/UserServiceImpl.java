package com.example.microuserservice.service;

import com.example.microuserservice.adapter.out.ResponseUserMapper;
import com.example.microuserservice.adapter.out.UserMapper;
import com.example.microuserservice.data.RequestUser;
import com.example.microuserservice.data.ResponseUser;
import com.example.microuserservice.data.UserDto;
import com.example.microuserservice.persistence.entity.UserEntity;
import com.example.microuserservice.port.in.UserService;
import com.example.microuserservice.port.out.UserSavePort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final ResponseUserMapper responseUserMapper;
    private final UserSavePort userSavePort;

    @Transactional
    @Override
    public UserDto createUser(RequestUser requestUser) {
        UserDto userDto = userMapper.requestToDto(requestUser);
        userDto.setUserId(UUID.randomUUID().toString());
        userDto.setEncPasswd(passwordEncoder.encode(userDto.getPwd()));
        userSavePort.save(userDto);
        return userDto;
    }
}
