package com.example.demo1.config;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class BlacklistToken {
	
	@Autowired
	private RedisTemplate<String,Object> redisTemplate;
	
	private final String blacklist_PREFIX="blacklisted";
	
	public void blacklistToken(String token,long expiration) {
		String key=blacklist_PREFIX+token;
		redisTemplate.opsForValue().set(key, "true", expiration, TimeUnit.MILLISECONDS);
	}
	public boolean isTokenBlackListed(String token) {
		String key=blacklist_PREFIX+token;
		return Boolean.TRUE.toString().equals(redisTemplate.opsForValue().get(key));
	}

}
