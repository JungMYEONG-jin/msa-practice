package com.example.microuserservice.data;

import lombok.Data;

@Data
public class RequestUser {
    private String name;
    private String email;
    private String pwd;
}
