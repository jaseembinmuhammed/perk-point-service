package com.service.perkpoint.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.service.perkpoint.security.JwtService;
import com.service.perkpoint.security.UserPrincipal;

import jakarta.validation.Valid;

@Service
public class AuthService {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtService jwtService;

	public LoginResponse login(@Valid LoginRequest request) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
		String jwt = jwtService.createJwt(userDetails);
		String refreshToken =null;// refreshTokenService.generateRefreshToken(jwt);
		return new LoginResponse(jwt, refreshToken, userDetails);
	}

}
