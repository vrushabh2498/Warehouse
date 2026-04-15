package com.wms.temp.serviceImpl;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.wms.temp.Repository.AuthRepository;
import com.wms.temp.dto.LoginRequestDto;
import com.wms.temp.dto.UserRequestDto;
import com.wms.temp.dto.UserResponseDto;
import com.wms.temp.entity.UserEntity;
import com.wms.temp.enums.RoleType;
import com.wms.temp.exception.UserAlreadyPresentException;
import com.wms.temp.exception.UserNotFoundException;
import com.wms.temp.security.JwtUtil;
import com.wms.temp.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	private final JwtUtil jwtutil;
	private final AuthRepository repository ;
	
	private UserServiceImpl (AuthRepository repository,JwtUtil jwtutil ) {
		this.repository=repository;
		this.jwtutil=jwtutil;
	}
	
	
	
	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public RoleType getRole(String role) {
	    try {
	        return RoleType.valueOf(role.toUpperCase());
	    } catch (Exception e) {
	        throw new RuntimeException("Invalid role");
	    }
	}
	
	

	
	@Override
	public com.wms.temp.entity.UserEntity register(UserRequestDto request) {
		boolean present=repository.existsByEmail(request.getEmail());
		if(present==true) {
			throw new UserAlreadyPresentException("user already present with this email:" +request.getEmail());
		}
		RoleType role = request.getRole();

		if (role == null || role.isBlank()) {
		    throw new RuntimeException("Role cannot be empty");
		}
		
		
		UserEntity user = UserEntity.builder()
		        .name(request.getName())
		        .email(request.getEmail())
		        .password(passwordEncoder.encode(request.getPassword()))
		        .role(role)
		        .build();
		
		return repository.save(user);
		
		
		
	}

	@Override
	public String login(LoginRequestDto request) {
	    UserEntity userLogin = repository.findByEmail(request.getEmail())
	            .orElseThrow(() -> new UserNotFoundException("user not found"));

	    if (!passwordEncoder.matches(request.getPassword(), userLogin.getPassword())) {
	        throw new RuntimeException("invalid credentials");
	    }
	    
	     String token = jwtutil.generateToken(request.getEmail());
	    return token;

	   
	}
	
	
		
		
		
	}


