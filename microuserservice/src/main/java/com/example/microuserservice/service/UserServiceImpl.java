package com.example.microuserservice.service;

import com.example.microuserservice.adapter.out.ResponseUserMapper;
import com.example.microuserservice.adapter.out.UserMapper;
import com.example.microuserservice.data.RequestUser;
import com.example.microuserservice.data.ResponseUser;
import com.example.microuserservice.data.UserDto;
import com.example.microuserservice.persistence.entity.UserEntity;
import com.example.microuserservice.port.in.UserService;
import com.example.microuserservice.port.out.UserFindPort;
import com.example.microuserservice.port.out.UserSavePort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserSavePort userSavePort;
    private final UserFindPort userFindPort;
    private final ResponseUserMapper responseUserMapper;

    @Transactional
    @Override
    public UserDto createUser(RequestUser requestUser) {
        UserDto userDto = userMapper.requestToDto(requestUser);
        userDto.setUserId(UUID.randomUUID().toString());
        userDto.setEncPasswd(passwordEncoder.encode(userDto.getPwd()));
        userSavePort.save(userDto);
        return userDto;
    }

    @Override
    public ResponseUser getUserByUserId(String userId) {
        UserDto user = userFindPort.findUserByUserId(userId);
        if (user == null)
            throw new UsernameNotFoundException("User Not Found");
        ResponseUser responseUser = responseUserMapper.dtoToResponse(user);
        responseUser.setOrders(new ArrayList<>());
        return responseUser;
    }

    @Override
    public List<ResponseUser> getAllUser() {
        List<ResponseUser> collect = userFindPort.findAll().stream().map(it -> responseUserMapper.dtoToResponse(it)).collect(Collectors.toList());
        collect.forEach(it -> it.setOrders(new ArrayList<>()));
        return collect;
    }
}
