package net.project.banking.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import net.project.banking.dto.JwtResponse;
import net.project.banking.dto.RefreshTokenRequest;
import net.project.banking.dto.SignInRequest;
import net.project.banking.dto.SignUpRequest;
import net.project.banking.model.Users;

@Service
public interface AuthService {

	public Users signup(SignUpRequest signUpRequest);
	public JwtResponse signIn(SignInRequest signInRequest);
	public JwtResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
