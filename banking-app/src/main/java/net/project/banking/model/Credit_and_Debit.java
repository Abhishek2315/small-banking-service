package net.project.banking.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import net.project.banking.Status;
import net.project.banking.Transaction_type;

@Table(name = "credit_and_debit")
@Entity
@Data
public class Credit_and_Debit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "sender_account_id")
	private Accounts senderAccount;
	
	@ManyToOne
	@JoinColumn(name = "receiver_account_id")
	private Accounts receiverAccount;
	
	@OneToOne
	@JoinColumn(name = "transaction_id")
	private Transaction transactionId;
	
	@Column(name = "amount")
	private BigDecimal amount;
	
	@Column(name = "transaction_date")
	private LocalDateTime transactionDate;

}
