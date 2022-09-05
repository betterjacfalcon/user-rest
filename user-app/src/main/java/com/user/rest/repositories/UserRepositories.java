package com.user.rest.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.rest.entities.Users;

@Repository
public interface UserRepositories extends JpaRepository<Users, Integer>{
	
	public Optional<Users> findByUserName(String userName);
	
	public Optional<Users> findByUserNameAndPassword(String userName, String password);
	
	public Optional<Users> findByPassword(String password);
}
