package net.project.banking.dto;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import net.project.banking.Role;

@Data
public class SignUpRequest {
	
	private String name;
	
	private String email;
	
	private String password;
	
}
