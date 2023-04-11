package com.oh.motoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class motoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(motoServiceApplication.class, args);
	}

}
