package com.example.demo1.impl;

import java.lang.System.Logger;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.aspectj.weaver.patterns.HasThisTypePatternTriedToSneakInSomeGenericOrParameterizedTypePatternMatchingStuffAnywhereVisitor;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

import com.example.demo1.Exception.UnauthorizedUser;
import com.example.demo1.model.Doctor;
import com.example.demo1.repo.DoctorRepo;


import lombok.extern.slf4j.Slf4j;
//@Service
//@Slf4j
public class UserDetailsImpl {
//implements UserDetailsService{
//	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UserDetailsImpl.class);
//	@Autowired
//	private DoctorRepo repo;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//		Doctor d=null;
//		
//		
//		d=this.repo.findByDoctorname(username).orElseThrow(()-> new RuntimeException("Not found this username"));
//		log.info("Role Is :"+ d.getRole());
//
//		List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + d.getRole()));
//
//	
//		return User.builder().username(d.getDoctor_name()).password(d.getPassword()).authorities(authorities).build();
//	}

}
