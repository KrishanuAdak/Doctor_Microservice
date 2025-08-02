package com.example.demo1.util;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;

//@Component

public class JwtUtil {
//	
//	private final String SECRET_KEY="hfihsdfiuifh8098nofhfhfih@hoihfohhkoolvhhncvbkhihdifheioheoe0fe0ehiheurjhfhfksnbsdnsdfsijfhihfsdjhbfsigfsfifsfsff";
//	
//	private SecretKey getSiginingKey() { 
//		return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
//	}
//	
//	public String createToken(Map<String,Object> claims,String username) {
//		return Jwts.builder()
//				.setClaims(claims)				
//				.setSubject(username)				
//				.setIssuedAt(new Date(System.currentTimeMillis()))
//				.setExpiration(new Date(System.currentTimeMillis()+1000*60*60*5)) 
//				.signWith(getSiginingKey())
//				.compact(); 
//		
//	}
//
//    public String generateToken(UserDetails userDetails) {
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("roles", userDetails.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.toList()));
//        System.out.println("Role from Util : "+userDetails.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.toList()));
//        
//        return createToken(claims, userDetails.getUsername());
//    }
//    
//	public Claims extractAllClaims(String token) {
//		return Jwts.parserBuilder()
//				.setSigningKey(getSiginingKey())
//				.build().parseClaimsJws(token).getBody();
//		
//	}
//
//	public String extractRole(String token) {
//	    return extractAllClaims(token).get("role", String.class);
//	}
//
//
//	
//	public String extractEmail(String token)
//	{
//		return extractAllClaims(token).getSubject();
//	}
//	
//	public Date getExpiration(String token) {
//		return extractAllClaims(token).getExpiration();
//	}
//	
//	public boolean isExpired(String token) {
//		return extractAllClaims(token).getExpiration().before(new Date(System.currentTimeMillis()));
//	}
//	public boolean isValidateToken(String token) {
//		return !isExpired(token);
//	}

}
