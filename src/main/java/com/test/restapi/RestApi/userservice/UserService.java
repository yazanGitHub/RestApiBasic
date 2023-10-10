package com.test.restapi.RestApi.userservice;

import com.test.restapi.RestApi.ui.model.request.UserDetailsRequestModule;
import com.test.restapi.RestApi.ui.model.response.UserRest;

public interface UserService {
	UserRest createUser(UserDetailsRequestModule userDetails);
}
