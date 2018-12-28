package com.upm.es.grupof.users.controller;


import com.upm.es.grupof.users.entities.User;
import com.upm.es.grupof.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	UserService service;

	@RequestMapping(value = "/signup",
	method = RequestMethod.POST,
	consumes = "application/json",
	produces = "application/json")
	public void signup(@RequestBody User user){
		this.service.createUser(user);
	}

	@RequestMapping(value = "/login",
	method = RequestMethod.POST,
	consumes = "application/json",
	produces = "application/json")
	public void login(@RequestBody User user){
		this.service.logUserIn(user);
	}

	@RequestMapping(value = "/delete",
			method = RequestMethod.POST,
			consumes = "application/json",
			produces = "application/json")
	public void deleteUser(@RequestBody User user){
		this.service.deleteUser(user);
	}
}
