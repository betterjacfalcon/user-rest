package com.user.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.rest.entities.Profile;

@Repository
public interface ProfileRepositories extends JpaRepository<Profile, Integer>{

}
