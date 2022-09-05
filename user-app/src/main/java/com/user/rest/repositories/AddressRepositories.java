package com.user.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.rest.entities.Address;

@Repository
public interface AddressRepositories extends JpaRepository<Address, Integer>{

}
