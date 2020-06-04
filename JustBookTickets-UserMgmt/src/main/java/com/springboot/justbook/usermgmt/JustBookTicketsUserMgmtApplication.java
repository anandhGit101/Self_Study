package com.springboot.justbook.usermgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableConfigurationProperties
@EnableAutoConfiguration(exclude={JmxAutoConfiguration.class})
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200"})
public class JustBookTicketsUserMgmtApplication {

	public static void main(String[] args) {
		SpringApplication.run(JustBookTicketsUserMgmtApplication.class, args);
	}

}
