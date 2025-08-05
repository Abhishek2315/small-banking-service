package net.project.banking.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import net.project.banking.model.Accounts;
import net.project.banking.model.Transaction;

@Service
public interface TransactionService {

	Object depositAmount(String accountNumber,String accountType, BigDecimal depositAmount);

	Object withDrawAmount(String accountNumber, String accountType, BigDecimal withDrawAmount);

	Object transferAmount(String senderAccount,String accountType, String receiverAccount, BigDecimal transferAmount);

	List<Transaction> getTransactionList(String accountNumber);

	

}
