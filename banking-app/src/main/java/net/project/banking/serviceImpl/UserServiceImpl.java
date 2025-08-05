package net.project.banking.serviceImpl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import net.project.banking.Role;
import net.project.banking.model.Users;
import net.project.banking.repo.UsersRepo;
import net.project.banking.service.UsersService;

@Service
public class UserServiceImpl implements UsersService {
	
	@Autowired
	private UsersRepo usersRepo;
	
	
	@Override 
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				// TODO Auto-generated method stub
				return usersRepo.findByEmail(username)
//						.orElseThrow(()-> new UsernameNotFoundException("User not found"));
						;
			}
		};
	}


	
	

}
