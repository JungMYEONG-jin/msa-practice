package com.example.microuserservice.adapter.out;

import com.example.microuserservice.data.RequestUser;
import com.example.microuserservice.data.UserDto;
import com.example.microuserservice.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    UserEntity dtoToEntity(UserDto userDto);
    UserDto entityToDto(UserEntity userEntity);
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "encPasswd", ignore = true)
    UserDto requestToDto(RequestUser requestUser);
}
