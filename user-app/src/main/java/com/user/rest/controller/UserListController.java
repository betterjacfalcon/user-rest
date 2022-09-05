package com.user.rest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.user.rest.models.User;
import com.user.rest.services.UserService;

@RestController
@RequestMapping("/v1/users")
public class UserListController {

	@Autowired
	private UserService userService;
	
	@GetMapping 
	public ResponseEntity<List<User>> getUsers(@RequestParam(value="startWith", required=false) String startWith){
	
		return new ResponseEntity<List<User>>(userService.getUsers(startWith), HttpStatus.OK );
	
	}
	
	@GetMapping(value="/{userName}")
	public ResponseEntity<User> getUserByUserName(@PathVariable("userName") String userName){		
		return new ResponseEntity<User>(userService.getUserByUserName(userName), HttpStatus.OK );
	
	}
	
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user){
		return new ResponseEntity<User> (userService.createUser(user), HttpStatus.CREATED);
	}
	
	@PutMapping (value="/{userName}")
	public ResponseEntity<User> updateUser(@PathVariable("userName") String userName, @RequestBody User user){
		return new ResponseEntity<User> (userService.updateUser(user, userName), HttpStatus.OK);
	}
	
	@DeleteMapping (value="/{userName}")
	public ResponseEntity<User> DeleteUser(@PathVariable("userName") String userName){
		userService.deleteUser(userName);
		return new ResponseEntity (HttpStatus.NO_CONTENT);
	}
}
