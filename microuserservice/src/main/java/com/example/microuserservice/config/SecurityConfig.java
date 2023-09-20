package com.example.microuserservice.config;

import com.example.microuserservice.filter.AuthenticationFilter;
import com.example.microuserservice.port.out.UserFindPort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig{
    private final CustomAuthenticationManager customAuthenticationManager;
    private final UserFindPort userFindPort;
    private final Environment environment;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().
                requestMatchers(new AntPathRequestMatcher("/h2-console/**"))
                .requestMatchers(new AntPathRequestMatcher( "/favicon.ico"))
                .requestMatchers(new AntPathRequestMatcher( "/css/**"))
                .requestMatchers(new AntPathRequestMatcher( "/js/**"))
                .requestMatchers(new AntPathRequestMatcher( "/img/**"))
                .requestMatchers(new AntPathRequestMatcher( "/lib/**"));
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(authorize ->
                        authorize.requestMatchers(new MvcRequestMatcher(introspector, "/**")).permitAll()
                                .requestMatchers(new MvcRequestMatcher(introspector, "/actuator/**")).permitAll()
//                                requestMatchers(new MvcRequestMatcher.Builder(introspector).pattern(HttpMethod.GET, "/users/**")).permitAll()
//                                .requestMatchers(new MvcRequestMatcher(introspector, "/greeting")).permitAll()
//                                .requestMatchers(new MvcRequestMatcher(introspector, "/welcome")).permitAll()
//                                .requestMatchers(new MvcRequestMatcher(introspector, "/health-check")).permitAll()
//                                .requestMatchers(new MvcRequestMatcher.Builder(introspector).pattern(HttpMethod.POST, "/users")).permitAll()
                                .anyRequest()
                                .authenticated())
                .addFilter(getAuthenticationFilter())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    private AuthenticationFilter getAuthenticationFilter() {
        return new AuthenticationFilter(customAuthenticationManager, userFindPort, environment);
    }
}
