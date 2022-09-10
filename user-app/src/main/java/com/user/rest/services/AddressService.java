package com.user.rest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.user.rest.entities.Address;
import com.user.rest.entities.Profile;
import com.user.rest.repositories.AddressRepositories;
import com.user.rest.repositories.ProfileRepositories;

@Service
public class AddressService {

	@Autowired
	private AddressRepositories addressRepository;
	
	@Autowired
	private ProfileRepositories profileRepository;

	
	public List<Address> findAddressByProfileAndUserId(Integer userId, Integer profileId) {
		// TODO Auto-generated method stub
		return addressRepository.findByProfileId(userId, profileId);
	}


	public Address createAddress(Integer userId, Integer profileId, Address address) {
		Optional <Profile> result = profileRepository.findByUserIdAndProfileId( userId, profileId);
		if (result.isPresent()) {
			address.setProfile(result.get());
			return addressRepository.save(address);
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Profile id %d doesn't exists", profileId, userId ));
		}	
	}
}
