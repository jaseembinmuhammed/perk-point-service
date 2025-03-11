package com.service.perkpoint.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	JwtService jwtService;

	@Autowired
	PpUserDetails userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String authHeader = request.getHeader("Authorization");
		String token = null;
		String phoneNumber = null;
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			try {
				token = authHeader.substring(7);
				if (jwtService.isValidToken(token)) {
					phoneNumber = jwtService.getTokenSubject(token);
				}

				if (phoneNumber != null) {
					UserDetails userDetails = userDetailsService.loadUserByUsername(phoneNumber);
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
							null, userDetails.getAuthorities());
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authToken);
				}

			} catch (ExpiredJwtException ex) {
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
				response.getWriter().write("Token Expired");
				return;
			} catch (MalformedJwtException ex) {
				throw new RuntimeException("Token is malformed", ex);
			} catch (SignatureException ex) {
				throw new RuntimeException("Invalid signature", ex);
			} catch (UnsupportedJwtException ex) {
				throw new RuntimeException("Unsupported token", ex);
			} catch (IllegalArgumentException ex) {
				throw new RuntimeException("Token is null or empty", ex);
			} catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something Went Wrong");
			}
		}

		filterChain.doFilter(request, response);

	}

}
