package com.service.perkpoint.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("v1/auth")
@CrossOrigin("*")
public class AuthController {

	@Autowired
	AuthService service;

	@PostMapping("login")
	public ResponseEntity<LoginResponse> Login(@Valid @RequestBody LoginRequest request) {
		LoginResponse response = service.login(request);
		return ResponseEntity.ok(response);
	}

}
