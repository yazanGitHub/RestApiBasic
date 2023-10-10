package com.test.restapi.RestApi.ui.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserDetailsRequestModule {

	@NotNull(message = "error null First Name")
	private String firstName;
	
	@NotNull(message = "error null Last Name")
	private String lastName;

	@NotNull(message = "error null Email")
	@Email
	private String email;

	@NotNull(message = "error null Password")
	@Size(min = 8, max = 16, message = "error on password length")
	private String password;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
