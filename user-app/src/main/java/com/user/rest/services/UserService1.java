package com.user.rest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.user.rest.entities.Users;
import com.user.rest.repositories.UserRepositories;

@Service
public class UserService1 {
	
	@Autowired
	private UserRepositories userRepository;
	
	public List<Users> getUsers(){			
		return 	userRepository.findAll();		
	}
	
	public Users getUserById(Integer userId) {
		return userRepository.findById(userId).orElseThrow(
		() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("user %d not found ", userId)));
		
	}
	
	public Users getUserByUserName(String userName) {
		return userRepository.findByUserName(userName).orElseThrow(
		() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("user %d not found ", userName)));
		
	}
	
	public Users getUserByPassword(String password) {
		return userRepository.findByPassword(password).orElseThrow(
		() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("user %d not found ", password)));
		
	}
	
	public Users getUserByUserNameAndPassword(String userName,String password ) {
		return userRepository.findByUserNameAndPassword(userName, password).orElseThrow(
		() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("user %s not found ", userName, password)));
		
	}
}
