package com.example.microuserservice.adapter.out;

import com.example.microuserservice.data.RequestUser;
import com.example.microuserservice.data.UserDto;
import com.example.microuserservice.persistence.entity.UserEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity dtoToEntity(UserDto userDto);
    UserDto requestToDto(RequestUser requestUser);
}
