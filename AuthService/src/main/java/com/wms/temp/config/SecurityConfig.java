package com.wms.temp.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.wms.temp.security.JwtFilter;
@EnableWebSecurity
@Configuration
public class SecurityConfig {
	
	
	private JwtFilter jwtFilter;
	public SecurityConfig(JwtFilter jwtFilter) {
	this.jwtFilter=jwtFilter;
	}
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) {
		
		http
        .csrf(csrf -> csrf.disable())
        .sessionManagement(session -> session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/v1/auth/register", "/api/v1/auth/login").permitAll()
            .anyRequest().authenticated()
        )
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
        .httpBasic(httpBasic -> httpBasic.disable())   
        .formLogin(form -> form.disable());            

    return http.build();
	}
	
	@Bean
	public org.springframework.security.crypto.password.PasswordEncoder passwordEncoder() {
	    return new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
	}
}
