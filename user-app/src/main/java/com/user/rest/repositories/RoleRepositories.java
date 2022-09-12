package com.user.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.rest.entities.Role;

@Repository
public interface RoleRepositories extends JpaRepository<Role, Integer>{

}
