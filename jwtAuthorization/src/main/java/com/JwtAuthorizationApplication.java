package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class JwtAuthorizationApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtAuthorizationApplication.class, args);
	}
	
//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurer() {
//			public void addCorsMapping(CorsRegistry registry) {
//				registry.addMapping("/**").allowedOrigins("http://localhost:4200");
//			}
//		};
//	}
	
	//1 HomeController
	//2 JwtRequest
	/*
	 * 3 Jwt Response
	 * 4 CustomerUserDeatsilService
	 * 5 MySecurity
	 * 6 JwtHelper
	 * 7 AuthenticationEntryPoint -- to change status code
	 * 8 JwtController
	 * 9 HomeController*/

}
