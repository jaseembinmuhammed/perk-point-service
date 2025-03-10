package com.service.perkpoint.auth;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {

	@NotBlank(message = "Email is not given")
	private String email;

	@NotBlank(message = "Password is not given")
	private String password;

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
