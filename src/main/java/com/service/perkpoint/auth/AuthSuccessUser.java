package com.service.perkpoint.auth;

import java.util.Set;
import java.util.stream.Collectors;

import com.service.perkpoint.auth.user.PpUser;

public class AuthSuccessUser {

	private String name;

	private String email;

	private Set<String> roles;

	public AuthSuccessUser(PpUser principal) {
		this.name = principal.getName();
		this.email = principal.getMail();
		this.roles = principal.getRoles().stream().map(role -> role.getName().toString()).collect(Collectors.toSet());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

}
