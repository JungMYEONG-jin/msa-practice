package com.example.microuserservice.adapter.out;

import com.example.microuserservice.data.UserDto;
import com.example.microuserservice.persistence.entity.UserEntity;
import com.example.microuserservice.port.out.UserFindPort;
import com.example.microuserservice.port.out.UserSavePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class UserRepositoryAdapter implements UserSavePort, UserFindPort {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserEntity save(UserDto userDto) {
        UserEntity userEntity = userMapper.dtoToEntity(userDto);
        return userRepository.save(userEntity);
    }

    @Override
    public UserDto findUserByUserId(String userId) {
        return userRepository.findByUserId(userId).map(user -> userMapper.entityToDto(user)).orElse(null);
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(user -> userMapper.entityToDto(user)).collect(Collectors.toList());
    }
}
