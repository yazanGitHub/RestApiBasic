package com.test.restapi.RestApi.userservice.imp;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.test.restapi.RestApi.ui.model.request.UserDetailsRequestModule;
import com.test.restapi.RestApi.ui.model.response.UserRest;
import com.test.restapi.RestApi.userservice.UserService;

@Service
public class UserServiceImp implements UserService{
	
	Map<String, UserRest>userMap;

	@Override
	public UserRest createUser(UserDetailsRequestModule userDetails) {
		UserRest user = new UserRest();
		user.setEmail(userDetails.getEmail());
		user.setFirstName(userDetails.getFirstName());
		user.setLastName(userDetails.getLastName());
		user.setUserId(userDetails.getPassword());

		String userId = UUID.randomUUID().toString();
		user.setUserId(userId);
		if(userMap == null)
			userMap = new HashMap<>();
		
		userMap.put(userId, user);
		return user;
	}

}
