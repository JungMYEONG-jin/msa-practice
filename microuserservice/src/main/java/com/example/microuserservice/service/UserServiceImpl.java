package com.example.microuserservice.service;

import com.example.microuserservice.adapter.out.UserMapper;
import com.example.microuserservice.client.OrderServiceClient;
import com.example.microuserservice.data.RequestUser;
import com.example.microuserservice.data.ResponseOrder;
import com.example.microuserservice.data.UserDto;
import com.example.microuserservice.decoder.FeignErrorDecoder;
import com.example.microuserservice.port.in.UserService;
import com.example.microuserservice.port.out.UserFindPort;
import com.example.microuserservice.port.out.UserSavePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserSavePort userSavePort;
    private final UserFindPort userFindPort;
    private final Environment environment;
    private final RestTemplate restTemplate;
    private final OrderServiceClient orderServiceClient;
    private final FeignErrorDecoder feignErrorDecoder;


    @Transactional
    @Override
    public UserDto createUser(RequestUser requestUser) {
        UserDto userDto = userMapper.requestToDto(requestUser);
        userDto.setUserId(UUID.randomUUID().toString());
        userDto.setEncPasswd(passwordEncoder.encode(userDto.getPwd()));
        userSavePort.save(userDto);
        return userDto;
    }

    @Override
    public UserDto getUserByUserId(String userId) {
        UserDto user = userFindPort.findUserByUserId(userId);
        if (user == null) {
            throw new UsernameNotFoundException("User Not Found");
        }

        // rest template
//        String orderUrl = String.format(environment.getProperty("order-service.url"), userId);
//        ResponseEntity<List<ResponseOrder>> response = restTemplate.exchange(orderUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<ResponseOrder>>() {
//        });
//        List<ResponseOrder> orders = response.getBody();

        // feign client
        List<ResponseOrder> orders = orderServiceClient.getOrders(userId);
        user.setOrders(orders);
        return user;
    }

    @Override
    public List<UserDto> getAllUser() {
        return userFindPort.findAll();
    }
}
