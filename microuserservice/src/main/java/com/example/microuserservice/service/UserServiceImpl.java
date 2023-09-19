package com.example.microuserservice.service;

import com.example.microuserservice.adapter.out.UserMapper;
import com.example.microuserservice.data.RequestUser;
import com.example.microuserservice.data.UserDto;
import com.example.microuserservice.port.in.UserService;
import com.example.microuserservice.port.out.UserFindPort;
import com.example.microuserservice.port.out.UserSavePort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserSavePort userSavePort;
    private final UserFindPort userFindPort;

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
    public UserDto getUserByUserId(String userId) {
        UserDto user = userFindPort.findUserByUserId(userId);
        if (user == null) {
            throw new UsernameNotFoundException("User Not Found");
        }
        return user;
    }

    @Override
    public List<UserDto> getAllUser() {
        return userFindPort.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto userByEmail = userFindPort.findUserByEmail(username);
        if (userByEmail == null) {
            throw new UsernameNotFoundException("User Not Found");
        }
        return new User(userByEmail.getEmail(), userByEmail.getEncPasswd(), true, true, true, true, new ArrayList<>());
    }
}
