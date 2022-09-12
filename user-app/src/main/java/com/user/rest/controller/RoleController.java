package com.user.rest.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.rest.entities.Role;
import com.user.rest.entities.Users;
import com.user.rest.services.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleController {

	@Autowired
	private RoleService service;
	
	private static final Logger log = LoggerFactory.getLogger(RoleController.class);
	
	@GetMapping
	public ResponseEntity<List<Role>> getRoles(){
		Authentication authetication = SecurityContextHolder.getContext().getAuthentication();
		log.info("name {} ", authetication.getName());
		log.info("principal {} ", authetication.getPrincipal());
		log.info("credenciales {} ", authetication.getCredentials());
		log.info("roles {} ", authetication.getAuthorities().toString());
		return new ResponseEntity<List<Role>>(service.getRoles(),HttpStatus.OK);
	}
	
	@GetMapping("/{roleName}/users")
	public ResponseEntity<List<Users>> getUserByRoles(@PathVariable("roleName") String roleName){
		return new ResponseEntity<List<Users>>(service.getUsersByRole(roleName),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Role> createRole(@RequestBody Role role){
		
		return new ResponseEntity<Role>(service.createRole(role), HttpStatus.CREATED);
	}
	
	@PutMapping("/{roleId}")
	public ResponseEntity<Role> updateRole(@PathVariable("roleId") Integer roleId, @RequestBody Role role){
		
		return new ResponseEntity<Role>(service.updateRole(roleId, role), HttpStatus.OK);
	}
	
	@DeleteMapping("/{roleId}")
	public ResponseEntity<Void> deleteRole(@PathVariable("roleId") Integer roleId, @RequestBody Role role){
		service.deleteRole(roleId);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
