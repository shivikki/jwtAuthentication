package com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	//make api private ie once user authenticated then only able to access api
	@RequestMapping("/welcome")
	public String welcome() {
		return "Not allowed for unauthorized user";
	}
}
