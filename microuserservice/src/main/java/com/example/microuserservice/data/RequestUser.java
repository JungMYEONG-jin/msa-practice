package com.example.microuserservice.data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestUser {
    @NotBlank(message = "이름은 필수입력값입니다.")
    private String name;
    @NotBlank(message = "Email은 공백이 불가합니다.")
    @Email
    private String email;
    @Size(min = 8, message = "패스워드는 최소 8자 이상입니다.")
    private String pwd;
}
