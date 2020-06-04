package com.springboot.justbook.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class JustBookTicketsEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JustBookTicketsEurekaServerApplication.class, args);
	}

}
