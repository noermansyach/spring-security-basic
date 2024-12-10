package com.ss.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ss.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByUsername(String username);
	
}
