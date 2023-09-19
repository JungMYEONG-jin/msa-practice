package com.example.microuserservice.service;

import com.example.microuserservice.data.UserDto;
import com.example.microuserservice.port.out.UserFindPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserFindPort userFindPort;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto userByEmail = userFindPort.findUserByEmail(username);
        System.out.println("userByEmail = " + userByEmail);
        if (userByEmail == null) {
            throw new UsernameNotFoundException("User Not Found");
        }
        return new User(userByEmail.getEmail(), userByEmail.getEncPasswd(), true, true, true, true, new ArrayList<>());
    }
}
