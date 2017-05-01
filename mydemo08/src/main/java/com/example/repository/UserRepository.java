package com.example.repository;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.User;

@CacheConfig(cacheNames="users")
public interface UserRepository extends JpaRepository<User, Long>{

	@Cacheable
    User findByName(String name);
	
	
}
