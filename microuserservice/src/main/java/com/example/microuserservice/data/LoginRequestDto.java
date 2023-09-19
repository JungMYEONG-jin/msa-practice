package com.example.microuserservice.data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {
    @NotNull(message = "Email can not be null")
    @Size(min = 2, message = "Please typing more than 2 characters")
    @Email
    private String email;

    @NotNull(message = "Password can not be null")
    @Size(min = 8, message = "Password must be equal or greater than 8 characters")
    private String password;
}
