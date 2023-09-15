package com.example.microuserservice.adapter.out;

import com.example.microuserservice.data.UserDto;
import com.example.microuserservice.persistence.entity.UserEntity;
import com.example.microuserservice.port.out.UserSavePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserRepositoryAdapter implements UserSavePort {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserEntity save(UserDto userDto) {
        UserEntity userEntity = userMapper.dtoToEntity(userDto);
        return userRepository.save(userEntity);
    }
}
