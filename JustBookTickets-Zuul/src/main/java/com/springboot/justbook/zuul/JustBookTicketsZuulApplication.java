package com.springboot.justbook.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.springboot.justbook.zuul.filters.ErrorFilter;
import com.springboot.justbook.zuul.filters.PostFilter;
import com.springboot.justbook.zuul.filters.PreFilter;
import com.springboot.justbook.zuul.filters.RouteFilter;

@SpringBootApplication
@EnableZuulProxy
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200", "http://localhost:8899" }) 
public class JustBookTicketsZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(JustBookTicketsZuulApplication.class, args);
	}

    @Bean
    public PreFilter preFilter() {
        return new PreFilter();
    }
    @Bean
    public PostFilter postFilter() {
        return new PostFilter();
    }
    @Bean
    public ErrorFilter errorFilter() {
        return new ErrorFilter();
    }
    @Bean
    public RouteFilter routeFilter() {
        return new RouteFilter();
    }

}
