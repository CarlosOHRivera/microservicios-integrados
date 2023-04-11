package com.usuario.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


// Aquí me he quedado -- que no guarda el id del cliente en el POST http://localhost:8002/usuario/carro/2
// https://youtu.be/icTg6iTqpUk?t=5915
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication

public class UsuarioServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsuarioServiceApplication.class, args);
	}

}
