package net.project.banking.serviceImpl;

import java.time.LocalDateTime;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.project.banking.Role;
import net.project.banking.dto.JwtResponse;
import net.project.banking.dto.RefreshTokenRequest;
import net.project.banking.dto.SignInRequest;
import net.project.banking.dto.SignUpRequest;
import net.project.banking.model.Users;
import net.project.banking.repo.UsersRepo;
import net.project.banking.security.JwtHelper;
import net.project.banking.service.AuthService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
	
	private final UsersRepo usersRepo;
	
	private final PasswordEncoder passwordEncoder;
	
	private final AuthenticationManager authenticationManager;
	
	private final JwtHelper jwtHelper;
	
	public Users signup(SignUpRequest signUpRequest) {
		
		Users users = new Users();
		
		users.setEmail(signUpRequest.getEmail());
		users.setName(signUpRequest.getName());
		users.setRole(Role.USER);
		users.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
		users.setCreated_at(LocalDateTime.now());
		return usersRepo.save(users);
		
	}
	
	public JwtResponse  signIn(SignInRequest signInRequest) {
		JwtResponse jwtResponse = new JwtResponse();
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(),
					signInRequest.getPassword()));
			
			var user = usersRepo.findByEmail(signInRequest.getEmail());
			var jwt = jwtHelper.generateToken(user);
			var refreshToken = jwtHelper.generateRefreshToken(new HashMap<>(), user);
			
			jwtResponse.setJwtToken(jwt);
			jwtResponse.setRefreshToken(refreshToken);
			jwtResponse.setUsername(signInRequest.getEmail());
			
			
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		return jwtResponse;
	}
	
	
	
	public JwtResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
		JwtResponse jwtResponse = new JwtResponse();
		
		String userEmail  = jwtHelper.getUsernameFromToken(refreshTokenRequest.getRefreshToken());
		Users user = usersRepo.findByEmail(userEmail);
		
		if(jwtHelper.validateToken(refreshTokenRequest.getRefreshToken(), user)) {
			var jwt = jwtHelper.generateToken(user);
			
			jwtResponse.setJwtToken(jwt);
			jwtResponse.setRefreshToken(refreshTokenRequest.getRefreshToken());
			jwtResponse.setUsername(user.getEmail());
			return jwtResponse;
		}
		
		
		return null;
		
	}
}
