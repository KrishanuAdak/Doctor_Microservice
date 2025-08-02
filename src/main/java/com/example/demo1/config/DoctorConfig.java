package com.example.demo1.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.ProviderManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo1.filter.JwtFilter;
import com.example.demo1.impl.UserDetailsImpl;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;

@Configuration


public class DoctorConfig {
//	@Autowired(required=true)
//	private PasswordEncoder encoder;
	
//	@Autowired(required=true)
//	private UserDetailsImpl userDetails;
	
//	@Autowired
//	@Lazy
//	private JwtFilter jwtFilter;
	
	
	
	    @Bean
	    public WebMvcConfigurer corsConfigurer() {
	        return new WebMvcConfigurer() {
	            @Override
	            public void addCorsMappings(CorsRegistry registry) {
	                registry.addMapping("/api/**") // Adjust path as needed
	                        .allowedOrigins("http://localhost:4200")
	                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
	                        .allowedHeaders("*")
	                        .allowCredentials(true);
	            }
	        };
	    }
	    
//	    @Bean
//	    public SecurityFilterChain secureRequests(HttpSecurity http) throws Exception {
//	    	
////	    	http.csrf().disable().authorizeHttpRequests(auth->auth.anyRequest().permitAll());
//	    	
//	    	http.csrf()
//	    	.disable()
//	    	.authorizeHttpRequests(auth-> auth.requestMatchers("/doctor/login","/doctor/register","localhost:9093/admin/doctors/list").permitAll()
//
// 	    			.requestMatchers("/doctor/**").hasAnyRole("DOCTOR","ADMIN")
//
//
//	    			
//	    			.anyRequest().authenticated())
//	    	.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//	    	.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
//	    	.logout().disable();
//	    	
	    	
	    	
	    	
	    	
	    	
//	    	.logout(logout-> logout.logoutUrl("/logout")
//	    			.logoutSuccessUrl("/login") 
//	    			.invalidateHttpSession(true)
//	    			.clearAuthentication(true));
	    		
//	    	return http.build();
//	    	
//	    }
//	    
	    
	    
	
//	   @Bean
//	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//	       http.csrf().disa
//	        return http.build();
//	    }
//	@Bean
//	public UserDetailsService userDetails()
//	{
//	    String adminPassword = passwordEncode().encode("12345");
//	    String userPassword = passwordEncode().encode("1234");
//		UserDetails u1=User.builder().username("admin").password(adminPassword).roles("ADMIN").build();		
//		UserDetails u2=User.builder().username("Krish").password(userPassword).roles("ADMIN").build();		
//		System.out.println(u1.getPassword()+" "+u1.getAuthorities());
//		return new InMemoryUserDetailsManager(u1,u2);
//		
//	}
	
	//FOR BCRYPT PASSWORD
//	@Bean
//	public PasswordEncoder passwordEncode()
//	{
//		return new BCryptPasswordEncoder();
//		
//	}
//	
//	@Bean
//	public AuthenticationManager authenticate(AuthenticationConfiguration config) throws Exception {
//		return config.getAuthenticationManager();
//	}
//	
//	@Bean
//	public AuthenticationProvider auth() {
//		DaoAuthenticationProvider auths=new DaoAuthenticationProvider();
//		auths.setUserDetailsService(userDetails);
//		auths.setPasswordEncoder(passwordEncode());
//		return auths;
		
//	}
    
	
	//JWT AUTHENTICATION
//	@Bean
//	public AuthenticationManager userdetailService(UserDetailsService userDetails)
//	{
//		DaoAuthenticationProvider authentication=new DaoAuthenticationProvider();
//		authentication.setUserDetailsService(userDetails);
//		authentication.setPasswordEncoder(passwordEncode());		
//		return new ProviderManager(List.of(authentication));
//		
//	}
	
	
	
	
	
//	@SuppressWarnings("rawtypes")
	@Bean
	public RedisTemplate redisTemplate(RedisConnectionFactory factory) throws Exception
	{
		RedisTemplate redisTemplate=new RedisTemplate<>();
		redisTemplate.setConnectionFactory(factory);
//		redisTemplate.setKeySerializer(new RedisSerializer());
//		redisTemplate.setValueSerializer(new RedisSerializer());
		return redisTemplate;
		
	}
//	@Bean
//	public ConsumerFactory<String,String> producerFactory()
//	{
//		Map<String,Object> hm=new HashMap<>();
//		hm.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
//		hm.put(ConsumerConfig.GROUP_ID_CONFIG, "group-id");
//		hm.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
//		hm.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
//		return new DefaultKafkaConsumerFactory<>(hm);
//	}
//	
//	@Bean
//	public ConcurrentKafkaListenerContainerFactory<String,String> kafkaListen()
//	{      
//		   ConcurrentKafkaListenerContainerFactory<String, String> concurrentFactory=new ConcurrentKafkaListenerContainerFactory<>();
//		   concurrentFactory.setConsumerFactory(producerFactory());
//		   return concurrentFactory;
//	}

}
