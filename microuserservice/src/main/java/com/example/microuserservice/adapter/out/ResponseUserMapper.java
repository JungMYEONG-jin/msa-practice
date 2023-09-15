package com.example.microuserservice.adapter.out;

import com.example.microuserservice.data.ResponseUser;
import com.example.microuserservice.data.UserDto;
import com.example.microuserservice.persistence.entity.UserEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ResponseUserMapper {
//    @Mapping(target = "id", ignore = true)
    ResponseUser entityToResponse(UserEntity userEntity);
    ResponseUser dtoToResponse(UserDto userDto);
}
