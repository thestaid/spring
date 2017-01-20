package com.gura.spring.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gura.spring.users.service.UsersService;

@Controller
public class UsersController {

	@Autowired
	private UsersService usersService;
	
	@RequestMapping("/users/signup_form")
	public String signup_form(){
		return "users/signup_form";
	}
}
