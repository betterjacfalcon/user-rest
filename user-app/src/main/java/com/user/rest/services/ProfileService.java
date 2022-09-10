package com.user.rest.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.user.rest.entities.Profile;
import com.user.rest.entities.Users;
import com.user.rest.repositories.ProfileRepositories;
import com.user.rest.repositories.UserRepositories;

@Service
public class ProfileService {

	@Autowired
	private ProfileRepositories profileRepository;
	
	@Autowired
	private UserRepositories userRepository;
	
	public Profile create(Integer userId, Profile profile) {
		Optional<Users> result = userRepository.findById(userId);
		if (result.isPresent()) {
			profile.setUsers(result.get());
			return profileRepository.save(profile);
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User id %d doesn't exists", userId));
		}			
		
	}

	public Profile getUserId(Integer userId, Integer profileId) {
		// TODO Auto-generated method stub
		return profileRepository.findByUserIdAndProfileId(userId, profileId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("Profile not found user %d and profile %d", userId,profileId )));
	}
}
