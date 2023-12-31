package com.example.secondservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SecondserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecondserviceApplication.class, args);
	}

}
