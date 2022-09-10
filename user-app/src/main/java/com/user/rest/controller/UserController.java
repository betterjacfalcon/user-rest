package com.user.rest.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.user.rest.services.UserService1;

import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import com.user.rest.entities.Users;

@RestController 
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService1 userService;
	
	@GetMapping
	@Timed("get.users")
	public ResponseEntity<Page<Users>> getUsers(@RequestParam(required=false, value="page", defaultValue="0") int page, @RequestParam(required=false, value="size",defaultValue="100" ) int size){
		return new ResponseEntity<>(userService.getUsers(page, size), HttpStatus.OK);
	}
	
	@GetMapping("/usernames")
	public ResponseEntity<Page<String>> getUserNames (@RequestParam(required=false, value="page", defaultValue="0") int page, @RequestParam(required=false, value="size",defaultValue="100" ) int size){
		return new ResponseEntity<>(userService.getUserNames(page, size), HttpStatus.OK);
	}
	
	@GetMapping("/{userId}")
	@ApiOperation(value="Returns a user for a given user id", response=Users.class)
	@ApiResponses(value= {@ApiResponse(code=200, message="The record was found"),
			@ApiResponse(code=404, message="The record was not found")})
	
	public ResponseEntity<Users> getUserById(@PathVariable("userId") Integer userId){
		return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);	
	}
	
	@GetMapping("/userName/{userName}")
	public ResponseEntity<Users> getUserByUserName(@PathVariable("userName") String userName){
		return new ResponseEntity<>(userService.getUserByUserName(userName), HttpStatus.OK);	
	}
	
	@GetMapping("/password/{password}")
	public ResponseEntity<Users> getUserByPassword(@PathVariable("password") String password){
		return new ResponseEntity<>(userService.getUserByPassword(password), HttpStatus.OK);	
	}
	
	@PostMapping
	public ResponseEntity<Users> autheticate(@RequestBody Users users){
		return new ResponseEntity<>(userService.getUserByUserNameAndPassword(users.getUserName(), users.getPassword()), HttpStatus.OK);	
	}
	
	@DeleteMapping("/{userName}")
	public ResponseEntity<Void> deleteUsers(@PathVariable("userName") String userName){
		userService.deleteUserByUserName(userName);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);			
	}
}
