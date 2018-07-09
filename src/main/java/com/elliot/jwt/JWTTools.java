package com.elliot.jwt;

import java.security.Key;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTTools {
	private static final String secret = "test";

	

	public static JwtBuilder initJWTBuilder() {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;

		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		JwtBuilder jwtBuilder = Jwts.builder().signWith(signatureAlgorithm, signingKey);

		return jwtBuilder;
	}

	public static JwtBuilder addClaim(String key, String value, JwtBuilder jwtBuilder) {
		jwtBuilder.claim(key, value);

		return jwtBuilder;
	}

	public static String compactJwt(JwtBuilder jwtBuilder) {
		return jwtBuilder.compact();
	}

	public static String getClaim(String jwt, String key) {
		String claim = null;

		Claims body = extractJWTClaims(jwt);

		if (body != null) {
			claim = (String) body.get(key);
		}

		return claim;
	}

	public static boolean hasClaim(String jwt, String key) {
		String claim = null;

		Claims body = extractJWTClaims(jwt);

		return body.containsKey(key);

	}

	public static Claims extractJWTClaims(String jwt) {

		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;

		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		Claims body = null;

		body = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(jwt).getBody();

		return body;
	}

}
