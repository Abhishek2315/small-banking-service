package net.project.banking;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import net.project.banking.model.Users;
import net.project.banking.repo.UsersRepo;

@SpringBootApplication
public class BankingAppApplication implements CommandLineRunner  {
	
	@Autowired
	private UsersRepo usersRepo;

	public static void main(String[] args) {
		SpringApplication.run(BankingAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		Users adminAccount = usersRepo.findByRole(Role.ADMIN);
		if(adminAccount == null) {
			Users user = new Users();
			
			user.setName("Admin");
			user.setEmail("admin@gmail.com");
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			user.setRole(Role.ADMIN);
			user.setCreated_at(LocalDateTime.now());
			usersRepo.save(user);
		}
		
	}

}
