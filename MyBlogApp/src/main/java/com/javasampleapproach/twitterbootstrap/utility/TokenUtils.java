package com.javasampleapproach.twitterbootstrap.utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.javasampleapproach.twitterbootstrap.exception.SocioSeerException;


@Component
public class TokenUtils {

	@Value("${auth.token.secret}")
	private String secret;

	@Value("${auth.token.expiration}")
	private long expiration;

	public String getUserIdFromToken(String token) {
		String userId;
		try {
			final Claims claims = this.getClaimsFromToken(token);
			userId = claims.getSubject();
		} catch (Exception e) {
			throw new SocioSeerException(String.format(
					"Invalid or expired authorization token : %s", token));
		}
		return userId;
	}

	public Date getCreatedDateFromToken(String token) {
		Date created;
		try {
			final Claims claims = this.getClaimsFromToken(token);
			created = new Date((Long) claims.get("created"));
		} catch (Exception e) {
			created = null;
		}
		return created;
	}

	public Date getExpirationDateFromToken(String token) {
		Date expiration;
		try {
			final Claims claims = this.getClaimsFromToken(token);
			expiration = claims.getExpiration();
		} catch (Exception e) {
			expiration = null;
		}
		return expiration;
	}

	private Claims getClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}

	private Date generateCurrentDate() {
		return new Date(System.currentTimeMillis());
	}

	private Date generateExpirationDate() {
		return new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(30));
	}

	public boolean isTokenValid(String token) {
		final Date expiration = this.getExpirationDateFromToken(token);
		if (expiration == null) {
			return true;
		}
		return expiration.after(this.generateCurrentDate());
	}

	public String generateToken(String userId, long createTime) {
		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put("sub", userId);
		claims.put("created", createTime);
		return this.generateToken(claims);
	}

	private String generateToken(Map<String, Object> claims) {
		return Jwts.builder().setClaims(claims).setExpiration(this.generateExpirationDate())
				.signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, this.secret).compact();
	}
}
