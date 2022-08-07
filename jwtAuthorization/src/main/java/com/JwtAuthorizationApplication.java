package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JwtAuthorizationApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtAuthorizationApplication.class, args);
	}
	
	//1 HomeController
	//2 JwtRequest
	/*
	 * 3 Jwt Response
	 * 4 CustomerUserDeatsilService
	 * 5 MySecurity
	 * 6 JwtHelper
	 * 7 JwtController
	 */

}
