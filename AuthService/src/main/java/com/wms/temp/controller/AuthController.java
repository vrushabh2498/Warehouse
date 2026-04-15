package com.wms.temp.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wms.temp.dto.LoginRequestDto;
import com.wms.temp.dto.UserRequestDto;
import com.wms.temp.dto.UserResponseDto;
import com.wms.temp.entity.UserEntity;
import com.wms.temp.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	
	private final UserService service;
	
	public AuthController(UserService service) {
		this.service=service;
	}
	
	
	@PostMapping("/Register")
	public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto user){
		UserEntity savedUser=service.register(user);
		 UserResponseDto response = UserResponseDto.builder()
		            .id(savedUser.getId())
		            .username(savedUser.getName())
		            .email(savedUser.getEmail())
		            .build();
		 return ResponseEntity.ok(response);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@Valid @RequestBody LoginRequestDto request){
		String response = service.login(request);
        return ResponseEntity.ok(response);
	}

}
