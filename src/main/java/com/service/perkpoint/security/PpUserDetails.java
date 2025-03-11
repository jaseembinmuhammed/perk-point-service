package com.service.perkpoint.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.service.perkpoint.auth.credential.PpCredential;
import com.service.perkpoint.auth.credential.PpCredentialRepository;

@Component
public class PpUserDetails implements UserDetailsService {

	@Autowired
	PpCredentialRepository credentialRepo;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<PpCredential> userInfo = credentialRepo.findByMail(userName);
		return userInfo.map(UserPrincipal::new).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
	}

}
