package com.microservice.userprocessorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class UserProcessorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserProcessorServiceApplication.class, args);
	}

}
