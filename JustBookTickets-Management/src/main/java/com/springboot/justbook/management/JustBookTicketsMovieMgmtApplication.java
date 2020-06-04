package com.springboot.justbook.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200", "http://localhost:8899" })
@EnableFeignClients
@EnableDiscoveryClient
public class JustBookTicketsMovieMgmtApplication {

	public static void main(String[] args) {
		SpringApplication.run(JustBookTicketsMovieMgmtApplication.class, args);
	}

}
