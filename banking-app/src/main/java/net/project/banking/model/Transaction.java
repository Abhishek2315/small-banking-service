package net.project.banking.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.project.banking.Role;
import net.project.banking.Status;
import net.project.banking.Transaction_type;

//@NoArgsConstructor
//@AllArgsConstructor
@Table(name = "transactions")
@Entity
@Data
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "transaction_type")
	@Enumerated(EnumType.STRING)
	private Transaction_type transaction_type;
	
	@Column(name = "amount")
	private BigDecimal amount;
	
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@Column(name = "timestamp")
	private LocalDateTime timestamp;
	
	@ManyToOne
	@JoinColumn(name = "accountId")
	private Accounts accountId ;
}
