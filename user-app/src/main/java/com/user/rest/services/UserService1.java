package com.user.rest.services;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.user.rest.entities.Users;
import com.user.rest.repositories.UserRepositories;

@Service
public class UserService1 {
	private static final Logger log = LoggerFactory.getLogger(UserService1.class);
	
	@Autowired
	private UserRepositories userRepository;
	
	public Page<Users> getUsers(int page, int size){			
		return 	userRepository.findAll(PageRequest.of(page, size));		
	}
	
	public Users getUserById(Integer userId) {
		return userRepository.findById(userId).orElseThrow(
		() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("user %d not found ", userId)));
		
	}
	
	@CacheEvict("users")
	public void deleteUserByUserName(String userName) {
		Users users = getUserByUserName(userName);
		userRepository.delete(users);
	}	
	
	@Cacheable("users")
	public Users getUserByUserName(String userName) {
		log.info("Getting user by username{}", userName);
		return userRepository.findByUserName(userName).orElseThrow(
		() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("user %s not found ", userName)));
		
	}
	
	public Users getUserByPassword(String password) {
		return userRepository.findByPassword(password).orElseThrow(
		() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("user %d not found ", password)));
		
	}
	
	public Users getUserByUserNameAndPassword(String userName,String password ) {
		return userRepository.findByUserNameAndPassword(userName, password).orElseThrow(
		() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("user %s not found ", userName, password)));
		
	}

	public Page<String> getUserNames(int page,int size) {
		// TODO Auto-generated method stub
		return userRepository.getUserNames(PageRequest.of(page, size));
	}
	
		
}
