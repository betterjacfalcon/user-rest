package com.user.rest;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.javafaker.Faker;
import com.user.rest.entities.Role;
import com.user.rest.entities.UserInRole;
import com.user.rest.entities.Users;
import com.user.rest.repositories.RoleRepositories;
import com.user.rest.repositories.UserInRoleRepositories;
import com.user.rest.repositories.UserRepositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class UserAppApplication implements ApplicationRunner{
    
	@Autowired
	private Faker faker;
	
	@Autowired
	private UserRepositories repository;
	
	@Autowired
	private RoleRepositories roleRepository;
	
	@Autowired
	private UserInRoleRepositories userInRoleRepository;
	
	private static final Logger log = LoggerFactory.getLogger(UserAppApplication.class);

	
	public static void main(String[] args) {
		SpringApplication.run(UserAppApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
	
		Role roles[] = {new Role("ADMIN"), new Role("USER"), new Role("SUPPORT")};
		
		for(Role role: roles){
			roleRepository.save(role);
		}		
		for(int i = 0; i < 10 ; i++) {
			Users users = new Users();	
			users.setUserName(faker.friends().character());
			users.setPassword(faker.name().username());				
			Users created =repository.save(users);			
			UserInRole userInRole = new UserInRole(created, roles[new Random().nextInt(3)]);
			log.info("User created userName {}, password {} role {}",created.getUserName(), created.getPassword(), userInRole.getRole().getName());
			userInRoleRepository.save(userInRole);
		}
		
	}

}
