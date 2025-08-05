package net.project.banking.serviceImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import net.project.banking.Status;
import net.project.banking.Transaction_type;
import net.project.banking.model.Accounts;
import net.project.banking.model.Credit_and_Debit;
import net.project.banking.model.Transaction;
import net.project.banking.repo.AccountRepo;
import net.project.banking.repo.Credit_and_DebitRepo;
import net.project.banking.repo.TransactionRepo;
import net.project.banking.service.TransactionService;


@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private AccountRepo accountRepo;

	@Autowired
	private TransactionRepo transactionRepo;
	
	@Autowired
	private Credit_and_DebitRepo credit_and_DebitRepo;

	@Transactional
	public Object depositAmount(String accountNumber, String accountType, BigDecimal depositAmount) {
		Transaction transaction = new Transaction();
		Accounts account = accountRepo.findByAccountNumberAndAccountTypeAndStatus(accountNumber, "savings", "Active");

		if (ObjectUtils.isEmpty(account)) {
			Accounts nullAccount = accountRepo.findByAccountId(0L);

			transaction.setAmount(depositAmount);
			transaction.setStatus(Status.FAILED);
			transaction.setTimestamp(LocalDateTime.now());
			transaction.setTransaction_type(Transaction_type.DEPOSIT);
			transaction.setAccountId(nullAccount);
			transactionRepo.save(transaction);
			return ResponseEntity.status(200).body("Failed!! Account number is Invalid");
		} else {

			account.setBalance(account.getBalance().add(depositAmount));
			accountRepo.save(account);

			transaction.setAmount(depositAmount);
			transaction.setStatus(Status.SUCCESS);
			transaction.setTimestamp(LocalDateTime.now());
			transaction.setTransaction_type(Transaction_type.DEPOSIT);
			transaction.setAccountId(account);
			transactionRepo.save(transaction);
			return ResponseEntity.status(200).body("Amount Added Successfully!!");

		}

	}

	@Transactional
	public Object withDrawAmount(String accountNumber, String accountType, BigDecimal withDrawAmount) {
		
		Transaction transaction = new Transaction();
		Accounts account = accountRepo.findByAccountNumberAndAccountTypeAndStatus(accountNumber, "savings", "Active");

		if (!ObjectUtils.isEmpty(account)) {
			
			if(account.getBalance().compareTo(withDrawAmount) >= 0 ) {
				
				account.setBalance(account.getBalance().subtract(withDrawAmount));
				accountRepo.save(account);
				
				transaction.setAmount(withDrawAmount);
				transaction.setStatus(Status.SUCCESS);
				transaction.setTimestamp(LocalDateTime.now());
				transaction.setTransaction_type(Transaction_type.WITHDRAW);
				transaction.setAccountId(account);
				transactionRepo.save(transaction);
				return ResponseEntity.status(200).body("Amount WithDraw Successfully!!");
			}else {
				Accounts nullAccount = accountRepo.findByAccountId(0L);
				
				transaction.setAmount(withDrawAmount);
				transaction.setStatus(Status.FAILED);
				transaction.setTimestamp(LocalDateTime.now());
				transaction.setTransaction_type(Transaction_type.WITHDRAW);
				transaction.setAccountId(nullAccount);
				transactionRepo.save(transaction);
				return ResponseEntity.status(200).body("Not Sufficient Balance!!");
			}
		} else {
			return ResponseEntity.status(200).body("Failed!! Account number is Invalid");
		}
	}

	@Transactional
	public Object transferAmount(String senderAccount, String accountType, String receiverAccount,  BigDecimal transferAmount) {
		// TODO Auto-generated method stub
		Transaction transactionSender = new Transaction();
		Transaction transactionReceiver = new Transaction();
		Credit_and_Debit creditandDebit = new Credit_and_Debit();
		
		Accounts sender = accountRepo.findByAccountNumberAndAccountTypeAndStatus(senderAccount, accountType, "Active");
		Accounts receiver = accountRepo.findByAccountNumberAndAccountTypeAndStatus(receiverAccount, accountType, "Active");
		
		if(!ObjectUtils.isEmpty(sender) && !ObjectUtils.isEmpty(receiver)) {
			
			if(sender.getBalance().compareTo(transferAmount) >= 0 ) {
				sender.setBalance(sender.getBalance().subtract(transferAmount));
				receiver.setBalance(receiver.getBalance().add(transferAmount));
				accountRepo.save(sender);
				accountRepo.save(receiver);
				
			
			transactionSender.setAmount(transferAmount);
			transactionSender.setStatus(Status.SUCCESS);
			transactionSender.setTimestamp(LocalDateTime.now());
			transactionSender.setTransaction_type(Transaction_type.TRANSFERED);
			transactionSender.setAccountId(sender);
			transactionRepo.save(transactionSender);
			
			creditandDebit.setAmount(transferAmount);
			creditandDebit.setSenderAccount(sender);
			creditandDebit.setReceiverAccount(receiver);
			creditandDebit.setTransactionId(transactionSender);
			creditandDebit.setTransactionDate(LocalDateTime.now());
			credit_and_DebitRepo.save(creditandDebit);
			
			transactionReceiver.setAmount(transferAmount);
			transactionReceiver.setStatus(Status.SUCCESS);
			transactionReceiver.setTimestamp(LocalDateTime.now());
			transactionReceiver.setTransaction_type(Transaction_type.RECEIVED);
			transactionReceiver.setAccountId(receiver);
			transactionRepo.save(transactionReceiver);
			
			return ResponseEntity.status(200).body("Amount transfer Successfully!!");
			}else {
				transactionSender.setAmount(transferAmount);
				transactionSender.setStatus(Status.FAILED);
				transactionSender.setTimestamp(LocalDateTime.now());
				transactionSender.setTransaction_type(Transaction_type.TRANSFERED);
				transactionSender.setAccountId(sender);
				transactionRepo.save(transactionSender);
				return ResponseEntity.status(200).body("Insufficient Amount to Transfer");
			}
			
		}
		
		return ResponseEntity.status(200).body("Error!! Amount not transfer ");
	}

	@Override
	public List<Transaction> getTransactionList(String accountNumber) {
		// TODO Auto-generated method stub
//		Accounts account = accountRepo.findByAccountNumberAndAccountTypeAndStatus(accountNumber, "savings", "Active");
		
		return transactionRepo.findByAccountId(5L);
	}
	
}