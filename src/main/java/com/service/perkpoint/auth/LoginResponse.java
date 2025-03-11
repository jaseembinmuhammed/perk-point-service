package com.service.perkpoint.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class LoginResponse {

	private String jwt;

	private String refreshToken;

	private AuthSuccessUser principal;
	
	public LoginResponse(String jwt, String refreshToken, com.service.perkpoint.security.UserPrincipal userDetails) {
		this.jwt = jwt;
		this.principal = new AuthSuccessUser(userDetails.getPrincipal());
		this.refreshToken = refreshToken;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public AuthSuccessUser getPrincipal() {
		return principal;
	}

	public void setPrincipal(AuthSuccessUser principal) {
		this.principal = principal;
	}

}
