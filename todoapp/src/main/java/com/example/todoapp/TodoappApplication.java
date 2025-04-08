package com.example.todoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EntityScan("com.example.todoapp.model")
@EnableJpaRepositories("com.example.todoapp.repository")
public class TodoappApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoappApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
    	return new WebMvcConfigurer() {
        	@Override
        	public void addCorsMappings(@NonNull CorsRegistry registry) {
            	registry.addMapping("/**").allowedOrigins("http://localhost:3000");
        	}
    	};
	}
}
