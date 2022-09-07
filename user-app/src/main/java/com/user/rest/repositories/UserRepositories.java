package com.user.rest.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.user.rest.entities.Users;

/**
 * @author jacfa
 *
 */
@Repository
public interface UserRepositories extends JpaRepository<Users, Integer>{
	
	public Optional<Users> findByUserName(String userName);
	
	public Optional<Users> findByUserNameAndPassword(String userName, String password);
	
	public Optional<Users> findByPassword(String password);
	
	/**
	 * Esto no es SQL es JPQL
	 *
	 */
	@Query("SELECT u.userName FROM Users u")
	public Page<String> getUserNames(Pageable pageable);
	
	
}
