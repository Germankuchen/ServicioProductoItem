package com.ar.gk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class SpringBootServicioProductoItemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootServicioProductoItemApplication.class, args);
	}

}
