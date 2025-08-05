package net.project.banking.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtHelper  {
	
	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
	
	private String secret = "c2c81738d7ab1144e7386a26a87175353005ac0f06f106d259dff309e6fa5cdac2c81738d7ab1144e7386a26a87175353005ac0f06f106d259dff309e6fa5cda";
	
	// retrive username from jwt token
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}
	
	
	public <T> T getClaimFromToken(String token , Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFormToken(token);
		return claimsResolver.apply(claims);
		
	}
	
	//for retriving any information from token we will need the secret key
	private Claims getAllClaimsFormToken(String token) {
		return Jwts.parser().setSigningKey(getSignKey()).parseClaimsJws(token).getBody();
	}
	
	
	
	private Key getSignKey() {
		byte[] key = Decoders.BASE64.decode(secret);
		return Keys.hmacShaKeyFor(key);
	}
	
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());
	}
	
	//While creating the token -
	//1. Define claims of the token, like Issuer, Expiration, Subject, and the ID
	//2. Sign the JWT using the HS512 algorithm and secret Key.
	//3. According to JWS compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
	// compaction of the JWT to a URL-safe string
	
	private String doGenerateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))// 1000 * 60 * 24 --> 1 day
				.signWith(SignatureAlgorithm.HS512, getSignKey()).compact();
	}
	
	
	
	public String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails) {
		// TODO Auto-generated method stub
		return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 604800000))// 604800000--> 7 days
				.signWith(SignatureAlgorithm.HS512, getSignKey()).compact();
	}
	
	
	
	//Retrive Expiration date from jwt token
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}
	// check if the token has expired
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	
	public Boolean validateToken(String token, UserDetails userdetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userdetails.getUsername()) && !isTokenExpired(token));
	}


	
}
