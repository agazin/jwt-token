package com.agazin.jwttoken.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.agazin.jwttoken.component.UserComponent;
import com.agazin.jwttoken.model.request.UserLoginRequest;
import com.agazin.jwttoken.util.JwtUtil;

@RestController
public class MessageController {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserComponent userComponent;
	
	@GetMapping("/")
	public String getMesage() {
		return "spring boot with jwt";
	}

	@GetMapping("/profile")
	public ResponseEntity<Object> getUserProfile() {
		return ResponseEntity.ok(userComponent.getUserProfile());
	}

	@PostMapping("/generateToken")
	public String generateToken(@RequestBody UserLoginRequest request) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
		} catch (Exception ex) {
			throw new Exception("Invalid Credentials.");
		}

		return jwtUtil.generateToken(request.getUserName());
	}
}