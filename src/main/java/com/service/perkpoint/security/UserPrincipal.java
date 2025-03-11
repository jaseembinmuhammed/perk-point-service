package com.service.perkpoint.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.service.perkpoint.auth.credential.PpCredential;
import com.service.perkpoint.auth.user.PpUser;

@JsonInclude(Include.NON_NULL)
public class UserPrincipal implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String userName = null;
	private String password = null;
	private Set<SimpleGrantedAuthority> authorities;

	private PpUser principal;

	public UserPrincipal() {

	}

	public UserPrincipal(PpCredential credential) {
		PpUser user = credential.getUser();
		this.userName = user.getName();
		this.principal = credential.getUser();
		this.password = credential.getPassword();
		this.authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName().toString()))
				.collect(Collectors.toSet());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public PpUser getPrincipal() {
		return principal;
	}

	public void setPrincipal(PpUser principal) {
		this.principal = principal;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuthorities(Set<SimpleGrantedAuthority> authorities) {
		this.authorities = authorities;
	}

}
