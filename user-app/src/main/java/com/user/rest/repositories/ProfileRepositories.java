package com.user.rest.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.user.rest.entities.Profile;

@Repository
public interface ProfileRepositories extends JpaRepository<Profile, Integer>{

	@Query("SELECT p FROM Profile p WHERE p.users.id=?1 AND p.id=?2")
	Optional <Profile> findByUserIdAndProfileId(Integer userId, Integer profileId);

}
