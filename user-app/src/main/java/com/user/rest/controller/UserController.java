package com.user.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //Estereotipo
@RequestMapping("/Hello")
public class UserController {
	
	@GetMapping
	public String Hello() {
		return "Hello World";		
	}
}
