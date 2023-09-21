package com.example.microuserservice.data;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private String email;
    private String pwd;
    private String name;
    private String userId;
    private String encPasswd;
    private List<ResponseOrder> orders;
}
