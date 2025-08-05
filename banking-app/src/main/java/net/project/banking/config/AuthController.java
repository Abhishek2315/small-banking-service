//package net.project.banking.config;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import lombok.RequiredArgsConstructor;
//import net.project.banking.controller.UsersController;
//import net.project.banking.model.JwtRequest;
//import net.project.banking.model.JwtResponse;
//import net.project.banking.security.JwtHelper;
//
//
//@RestController
//@RequestMapping("/api/v1/auth/")
//@RequiredArgsConstructor
//public class AuthController {
//	
//	@Autowired
//	private UserDetailsService userDetailsService;
//
//	@Autowired
//	private AuthenticationManager authenticationManager;
//
//	@Autowired
//	private JwtHelper jwtHelper;
//
//	private Logger logger = LoggerFactory.getLogger(UsersController.class);
//	
//	@PostMapping("/login")
//	public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
//		this.doAuthenticate(request.getEmail(), request.getPassword());
//
//		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
//		String token = this.jwtHelper.generateToken(userDetails);
//
//		JwtResponse response = JwtResponse.builder().jwtToken(token)
//				.username(userDetails.getUsername()).build();
//		return new ResponseEntity<JwtResponse>(response, HttpStatus.OK);
//	}
//
//	private void doAuthenticate(String email, String password) {
//
//		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
//		try {
//			authenticationManager.authenticate(authentication);
//
//		} catch (BadCredentialsException e) {
//			throw new RuntimeException("Invalid Email and Password!!");
//		}
//	}
//
//}
