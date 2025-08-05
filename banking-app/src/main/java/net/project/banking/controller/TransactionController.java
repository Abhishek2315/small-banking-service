package net.project.banking.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import net.project.banking.model.Accounts;
import net.project.banking.model.Transaction;
import net.project.banking.service.AccountService;
import net.project.banking.service.TransactionService;

@RestController
@RequestMapping("/api/bank/transaction")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@PostMapping("/deposit")
	public Object depositAmount(@RequestParam("accountNumber") String accountNumber,
			@RequestParam("accountType") String accountType, @RequestParam("depositAmount") BigDecimal depositAmount) {

		return transactionService.depositAmount(accountNumber, accountType, depositAmount);
	}

	@PostMapping("/withDraw")
	public Object withDrawAmount(@RequestParam("accountNumber") String accountNumber,
			@RequestParam("accountType") String accountType,
			@RequestParam("withDrawAmount") BigDecimal withDrawAmount) {

		return transactionService.withDrawAmount(accountNumber, accountType, withDrawAmount);
	}

	@PostMapping("/Transfer")
	public Object transferAmount(@RequestParam("senderAccount") String senderAccount,
			@RequestParam("accountType") String accountType, @RequestParam("receiverAccount") String receiverAccount,
			@RequestParam("transferAmount") BigDecimal transferAmount) {

		return transactionService.transferAmount(senderAccount, accountType, receiverAccount, transferAmount);
	}
	
	@GetMapping("/getTransactionList")
	public List<Transaction> getTransactionList(@RequestParam("accountNumber") String accountNumber) {
		return transactionService.getTransactionList(accountNumber);
	}
}
