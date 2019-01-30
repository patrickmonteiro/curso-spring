package com.example.demo.controller;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.UserEntity;
import com.example.demo.request.UserControllerPostRequest;
import com.example.demo.response.UserControllerResponse;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserService userService;
	
	@GetMapping
	public List<UserControllerResponse> getUsers() {
		Type listType = new TypeToken<Iterable<UserControllerResponse>>() {}.getType();
        return new ModelMapper().map(userService.getAllUsers(), listType);
	}
	
	@PostMapping
	public UserControllerResponse createUser(@RequestBody UserControllerPostRequest request) {
		ModelMapper mapper = new ModelMapper();
		UserEntity entity = mapper.map(request, UserEntity.class);
		UserEntity stored = userService.createUser(entity);
		
		return mapper.map(stored, UserControllerResponse.class);
	}

}
