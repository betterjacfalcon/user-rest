package com.user.rest.services;

import java.util.List;
import java.util.Optional;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.user.rest.entities.Role;
import com.user.rest.entities.Users;
import com.user.rest.models.Devs4jSecurityRule;
import com.user.rest.repositories.RoleRepositories;
import com.user.rest.repositories.UserInRoleRepositories;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepositories repository;
	
	@Autowired
	private UserInRoleRepositories userInRolerepository;
	
	@Devs4jSecurityRule
	public List<Users> getUsersByRole(String roleName){
		return userInRolerepository.findUserByRole(roleName);
	}	
	 
	public List<Role> getRoles(){		
		return repository.findAll();
	}
	
	public Role createRole(Role role) {		
		return repository.save(role);
	}
	
	public Role updateRole(Integer roleId, Role role) {
		Optional<Role> result = repository.findById(roleId);		
		if(result.isPresent()) {
			return repository.save(role);
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Role id %d doesn't exists", roleId));
		}
	}

	public void deleteRole(Integer roleId) {
		Optional<Role> result = repository.findById(roleId);		
		if(result.isPresent()) {
			 repository.delete(result.get());
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Role id %d doesn't exists", roleId));
		}
		
	}
}
