package com.user.rest.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.user.rest.entities.UserInRole;
import com.user.rest.entities.Users;

@Repository
public interface UserInRoleRepositories extends CrudRepository<UserInRole, Integer>{
	
	@Query("SELECT u.user FROM UserInRole u WHERE u.role.name=?1")
	public List<Users> findUserByRole(String roleName);
	
	public List<UserInRole> findByUser(Users user);

}
