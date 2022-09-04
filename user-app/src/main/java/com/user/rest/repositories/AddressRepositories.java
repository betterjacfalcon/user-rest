package com.user.rest.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.user.rest.entities.Address;

@Repository
public interface AddressRepositories extends CrudRepository<Address, Integer>{

}
