package net.project.banking.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import net.project.banking.Role;
import net.project.banking.model.Users;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long> {
	
		String findByName(String name);
		
		// if getting error in any api change it String Typw
		Users findByEmail(String email);
		
		Users findByRole(Role role);

//		Users findByRole(Role admin);
}
