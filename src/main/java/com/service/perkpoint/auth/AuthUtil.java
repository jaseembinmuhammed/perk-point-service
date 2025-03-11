package com.service.perkpoint.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.service.perkpoint.auth.user.PpUser;
import com.service.perkpoint.security.UserPrincipal;

public class AuthUtil {
	
	public static PpUser getLoggedInUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.getPrincipal() == null) {
			return null;
		}
		try {
			UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
			return userDetails.getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}

}
