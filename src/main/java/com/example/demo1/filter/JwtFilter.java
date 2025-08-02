package com.example.demo1.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo1.config.BlacklistToken;
import com.example.demo1.impl.UserDetailsImpl;
import com.example.demo1.util.JwtUtil;

//import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



//@Component
public class JwtFilter {
//extends OncePerRequestFilter{
//	@Autowired
//	private JwtUtil jwtUtil;
//	
//	@Autowired
//	private UserDetailsImpl userdetails;
//	
//	@Autowired
//	private BlacklistToken token;
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		
//		String auth=request.getHeader("Authorization");
//		String jwt=null;
//		String user=null;
//		if(auth!=null && auth.startsWith("Bearer "))
//		{
//			jwt=auth.substring(7);
//			user=jwtUtil.extractEmail(jwt);
//		}
//		String path=request.getRequestURI();
//		if(path.startsWith("doctor/existsById")) {
//			filterChain.doFilter(request, response);
//			return;
//		}
//		if(token.isTokenBlackListed(jwt))
//		{
//			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//			response.getWriter().write("Token expired");
//			
//		}
//		if(user!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
//			Claims claims = jwtUtil.extractAllClaims(jwt);
//		      String role = jwtUtil.extractRole(jwt); // ⭐️ extract role from token
//
//	                UserDetails userD =  userdetails.loadUserByUsername(user);
//
//	                    UsernamePasswordAuthenticationToken authToken =
//	                            new UsernamePasswordAuthenticationToken(userdetails, null, userD.getAuthorities());
//	                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//	                    SecurityContextHolder.getContext().setAuthentication(authToken);
//	                }
//	
//	       
//
//		
//		
//		filterChain.doFilter(request, response);
//}
}
	
		
	


