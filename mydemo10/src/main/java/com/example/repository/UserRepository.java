package com.example.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.model.User;

/**
 * 感觉和jpa非常类似
 * @author mylinux
 *
 */
public interface UserRepository extends MongoRepository<User, Long>{

	User findByUsername(String username);
}
