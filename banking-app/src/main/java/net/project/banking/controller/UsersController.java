package net.project.banking.controller;

import org.apache.catalina.Manager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.project.banking.dto.JwtResponse;
import net.project.banking.model.JwtRequest;
import net.project.banking.model.Users;
import net.project.banking.repo.UsersRepo;
import net.project.banking.security.JwtHelper;
import net.project.banking.service.UsersService;

@RestController
@RequestMapping("/api/v1/user")
public class UsersController {

//	@Autowired
//	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtHelper jwtHelper;

	@Autowired
	private UsersService userService;
	
	@Autowired
	private UsersRepo usersRepo;

	private Logger logger = LoggerFactory.getLogger(UsersController.class);

//	@PostMapping("/register")
//	public ResponseEntity<String> addAcount(@RequestBody Users users) {
//		
////		String u = usersRepo.findByName(users.getName());
////		if(users.getName().equalsIgnoreCase(u)) {
////
////			return new ResponseEntity<String>("Username Already exist!", HttpStatus.OK);
////		}else {
//			
//			userService.addBankUser(users);
////		}
//		
//		return new ResponseEntity<String>("User Registered Successfully!", HttpStatus.CREATED);
//	}

	@GetMapping
	public String sayhello() {
		return "hello Users";
	}

}
