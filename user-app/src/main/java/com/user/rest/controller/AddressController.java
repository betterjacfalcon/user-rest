package com.user.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.rest.entities.Address;
import com.user.rest.services.AddressService;

@RestController
@RequestMapping("/users/{userId}/profiles/{profileId}/addresses")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@GetMapping
	public ResponseEntity<List<Address>> findAddressByProfileAndUserId(@PathVariable("userId") Integer userId, @PathVariable("profileId") Integer profileId){
		return new ResponseEntity<List<Address>>(addressService.findAddressByProfileAndUserId(userId, profileId), HttpStatus.OK);
		
	}
	
	@PostMapping
	public ResponseEntity<Address> create(@PathVariable("userId") Integer userId, @PathVariable("profileId") Integer profileId, @RequestBody Address address){
		return new ResponseEntity<Address>(addressService.createAddress(userId, profileId,address ),HttpStatus.CREATED );
		
	}
	
}
