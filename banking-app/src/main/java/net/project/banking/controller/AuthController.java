package net.project.banking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.project.banking.dto.JwtResponse;
import net.project.banking.dto.RefreshTokenRequest;
import net.project.banking.dto.SignInRequest;
import net.project.banking.dto.SignUpRequest;
import net.project.banking.model.Users;
import net.project.banking.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth/")
@RequiredArgsConstructor
public class AuthController {
		
	private final  AuthService authservice;
	
	@PostMapping("/signup")
	public ResponseEntity<Users> signUp(@RequestBody SignUpRequest signUpRequest ){
		
		
		return ResponseEntity.ok(authservice.signup(signUpRequest));
	}
	
	
	@PostMapping("/signIn")
	public ResponseEntity<JwtResponse> signIn(@RequestBody SignInRequest signInRequest){
		return ResponseEntity.ok(authservice.signIn(signInRequest));
	}
	
	@PostMapping("/refreshToken")
	public ResponseEntity<JwtResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
		return ResponseEntity.ok(authservice.refreshToken(refreshTokenRequest));
	}
}
