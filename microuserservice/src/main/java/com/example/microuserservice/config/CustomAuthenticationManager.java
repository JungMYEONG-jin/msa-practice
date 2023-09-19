package com.example.microuserservice.config;

import com.example.microuserservice.port.in.UserService;
import com.example.microuserservice.service.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomAuthenticationManager implements AuthenticationManager {
    private final CustomUserDetailService customUserDetailService;
    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("authentication = " + authentication.getName());
        UserDetails userDetails = customUserDetailService.loadUserByUsername(authentication.getName());
        if (!passwordEncoder().matches(authentication.getCredentials().toString(), userDetails.getPassword())) {
            System.out.println("Wrong password = ");
            throw new BadCredentialsException("Wrong password");
        }
        System.out.println(" login success ");
        System.out.println("userDetails username = " + userDetails.getUsername());
        System.out.println("userDetails password = " + userDetails.getPassword());
        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
    }
}
