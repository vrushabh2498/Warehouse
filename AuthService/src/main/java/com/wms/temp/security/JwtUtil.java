package com.wms.temp.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
@Component
public class JwtUtil {
	
	private final String SECRET = "mysecretkeymysecretkeymysecretkey"; 

    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());
    
    public String generateToken(String email) {
    	Map<String, Object> claims = new HashMap<>();
    	claims.put("role", "ROLE_ADMIN");
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
    
    
    public String extractEmail(String token) {
    	return Jwts.parserBuilder()
    			.setSigningKey(key)
    			.build()
    			.parseClaimsJws(token)
    			.getBody()
    			.getSubject();
    }
    
   
	
	public boolean validateToken(String token,String email) {
		 String extractedEmail = extractEmail(token);
		 return (extractedEmail.equals(email) && !isTokenExpired(token));
		
	}

	private boolean isTokenExpired(String token) {
		Date expiration=Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getExpiration();
		
		return expiration.before(new Date());
	}


	
	public String extractRole(String token) {
	    return extractAllClaims(token).get("role", String.class);
	}


	private Claims extractAllClaims(String token) {
		
		 Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

		    return Jwts.parserBuilder()
		            .setSigningKey(key)
		            .build()
		            .parseClaimsJws(token)
		            .getBody();
	}

	
	

}
