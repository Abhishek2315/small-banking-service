package net.project.banking.model;

import java.math.BigDecimal;
import java.security.Timestamp;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bank_accounts")
@Entity
@Data
public class Accounts {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accountId;
	
	@Column(name = "account_number")
	private String accountNumber;
	
	@Column(name = "account_holder_name")
	private String accountHolderName;
	
	@Column(name = "account_type")
	private String accountType;
	
	@Column(name = "balance")
	private BigDecimal balance;
	
	@Column(name = "currency")
	private String currency;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "created_time")
	private LocalDateTime createdTime;
	
	@Column(name = "last_transaction")
	private LocalDateTime lastTransaction;
	
}
