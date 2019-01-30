package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.UserEntity;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository repository;
	
	public Iterable<UserEntity> getAllUsers(){
		return repository.findAll();
	}
	
	public UserEntity createUser(UserEntity user) {
		user.setUserId("userIdteste");
		user.setEncryptedPassword("encrypted");
		UserEntity stored = repository.save(user);
		return stored;
	}

}
