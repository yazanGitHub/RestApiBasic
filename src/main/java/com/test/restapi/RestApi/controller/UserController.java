package com.test.restapi.RestApi.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.restapi.RestApi.ui.model.request.UserDetailsRequestModule;
import com.test.restapi.RestApi.ui.model.response.UserRest;
import com.test.restapi.RestApi.userservice.imp.UserServiceImp;

import jakarta.validation.Valid;

@RestController
@RequestMapping("users")
public class UserController {

	Map<String, UserRest>userMap;
	
	
	
	
	@Autowired
	UserServiceImp userService = new UserServiceImp();	
	// requestParameter /users?page=3&limit=50
	// defaultValue : allow to the param value to to be send it
	@GetMapping()
	public String getUsers
	(
		@RequestParam(value = "page", defaultValue = "1") int page,
		@RequestParam(value = "limit") int limit,
		@RequestParam(value = "sort", required = false, defaultValue = "4") int sort
	)
	{
		
		// this is to try the exception handler
//		String s = null;
//		int i = s.length();
//		int num = 2/0;
		return "get Users was called the page : " + page + " limit : " + limit + " sort : " + sort;
	}
	

	// pathVariable : /users/1
	// dont forget to add jackson-dataformat-xm dependency to get xml
	@GetMapping(path = "/{userId}",
				produces = { 
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE
					}
			   )
	public ResponseEntity<UserRest> getUser(@PathVariable String userId) 
	{
		if (userMap != null && userMap.containsKey(userId))
			return new ResponseEntity<>(userMap.get(userId), HttpStatus.OK);
		
		return new ResponseEntity<>( HttpStatus.NO_CONTENT);
	}
	

	@PostMapping(consumes = {
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE 
					},
				produces = { 
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE
					}
				)
	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModule userDetails) 
	{

		
		return new ResponseEntity<UserRest>(userService.createUser(userDetails), HttpStatus.OK);
		
		
	}

	
	@PutMapping(path = "/{userId}",
				consumes = {
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE 
				},
				produces = { 
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE
				})
	public UserRest updateUser(@PathVariable String userId, @RequestBody UserDetailsRequestModule userDetails) 
	{
		UserRest user = userMap.get(userId);
		
		//update the user details
		user.setFirstName(userDetails.getFirstName());
		user.setLastName(userDetails.getLastName());
		
		return user;
	}
	

	@DeleteMapping(path = "/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable String userId) 
	{
		if( userMap != null && userMap.containsKey(userId))
		{
			userMap.remove(userId);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return ResponseEntity.noContent().build();
	}

}
