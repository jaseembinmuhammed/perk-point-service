package com.service.perkpoint.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.service.perkpoint.base.PpStringUtil;

//import com.wandiary.service.WdStringUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {

	private static String GUEST_USER_SUBJECT = "Wandiary User";

	@Value("${jwt.secret}")
	private String JWT_SECRET;

	@Value("${refresh.jwt.secret}")
	private String REFRESH_TOKEN_SECRET;

	private static long EXPIRATION_DURATION = (1000 * 60 * 60 * 24) * 20;// 24 hours *20

	private static long REFRESH_TOKEN_EXPIRATION_DURATION = 1000 * 60 * 60 * 24 * 2;// 48 hours

	public String createJwt(UserPrincipal userDetails) {
		Map<String, Object> claims = new HashMap<>();

		return Jwts.builder().setClaims(claims).setSubject(userDetails.getPrincipal().getMail())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_DURATION))
				.signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
	}

	public String createJwt(String subject) {
		Map<String, Object> claims = new HashMap<>();

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_DURATION))
				.signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
	}

	public String createGuestJwt() {
		Map<String, Object> claims = new HashMap<>();

		return Jwts.builder().setClaims(claims).setSubject(GUEST_USER_SUBJECT)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_DURATION))
				.signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
	}

	private Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(JWT_SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public boolean isValidToken(String token) {
		Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
		return true;
	}

	public String getTokenSubject(String token) {
		try {
			Claims claims = Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
			return claims.getSubject();
		} catch (JwtException ex) {
			throw new RuntimeException("Failed to extract subject from token", ex);
		}
	}

	public String createRefreshToken(String tokenSubject) {
		Map<String, Object> claims = new HashMap<>();
		return Jwts.builder().setClaims(claims).setSubject(PpStringUtil.maskString(tokenSubject))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION_DURATION))
				.signWith(getRefreshSignKey(), SignatureAlgorithm.HS256).compact();
	}

	private Key getRefreshSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(REFRESH_TOKEN_SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}

}
