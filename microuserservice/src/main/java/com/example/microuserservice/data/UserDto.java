package com.example.microuserservice.data;

import lombok.Data;

@Data
public class UserDto {
    private String email;
    private String pwd;
    private String name;
    private String userId;
    private String encPasswd;
}
