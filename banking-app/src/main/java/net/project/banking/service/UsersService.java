package net.project.banking.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import net.project.banking.model.Users;

@Service
public interface UsersService {
	

	UserDetailsService userDetailsService();

}
