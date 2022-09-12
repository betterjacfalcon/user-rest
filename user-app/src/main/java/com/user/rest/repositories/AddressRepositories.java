package com.user.rest.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.user.rest.entities.Address;

@Repository
public interface AddressRepositories extends JpaRepository<Address, Integer>{

	@Query("SELECT a FROM Address a WHERE a.profile.users.id=?1 AND a.profile.id=?2")
	List<Address> findByProfileId(Integer userId, Integer profileId);
}
