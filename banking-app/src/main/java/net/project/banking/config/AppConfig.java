//package net.project.banking.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//@Configuration
//public class AppConfig {
//	
//	
//	
////	@Bean
////	public UserDetailsService userDetailsservice() {
////		
////		UserDetails admin = User.builder().username("Abhishek").password(passwordEncoder().encode("123456789")).roles("ADMIN").build();
////		UserDetails user = User.builder().username("Abhishek2315").password(passwordEncoder().encode("@Abhishek123")).roles("USER").build();
////		
////		return new InMemoryUserDetailsManager(admin, user);
////	}
////	
////	
////	 
////	@Bean
////	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
////		return config.getAuthenticationManager();
////	}
//
//}
