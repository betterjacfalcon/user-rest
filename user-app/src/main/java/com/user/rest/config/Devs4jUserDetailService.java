package com.user.rest.config;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.rest.entities.UserInRole;
import com.user.rest.entities.Users;
import com.user.rest.repositories.UserInRoleRepositories;
import com.user.rest.repositories.UserRepositories;

@Service
public class Devs4jUserDetailService implements UserDetailsService{
	
	@Autowired
	UserRepositories userRepository;
	
	@Autowired
	UserInRoleRepositories userInRoleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<Users> optional = userRepository.findByUserName(userName);
		
				if (optional.isPresent()) {
				Users user = optional.get();
				List<UserInRole> userInRoles = userInRoleRepository.findByUser(user);
				String[] roles = 
				userInRoles.stream()
				.map(r->r.getRole().getName())
				.toArray(String[]::new);
				
				
				
				
				return
				org.springframework.security.core.userdetails.
				User.withUsername(user.getUserName())
				.password(passwordEncoder.encode(user.getPassword()))
				.roles(roles).build();
				} else {
				throw new
				UsernameNotFoundException("Username " + userName +
				" not found");
				}			

	}

}
