package net.project.banking.dto;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
	
	private String accountHolderName;
	private String accountType;
	private BigDecimal balance;
	private String currency;
	
}
