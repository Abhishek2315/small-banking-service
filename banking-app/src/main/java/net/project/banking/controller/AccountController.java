package net.project.banking.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.project.banking.dto.AccountDto;
import net.project.banking.model.Accounts;
import net.project.banking.service.AccountService;

@RestController
@RequestMapping("/api/bank/account")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping("/hello")
	public String hello() {
		return "hello string";
	}
	
	@PostMapping("/createAccount")
	public ResponseEntity<Accounts> addAcount(@RequestBody Accounts account){
		return new ResponseEntity<Accounts>(accountService.addAccount(account), HttpStatus.CREATED);
	}
	
//	@PostMapping("/getByAccountNumber")
//	public ResponseEntity<Accounts> searchByAccountNumber(@RequestBody String accountNumber){
//		return new ResponseEntity<Accounts>(accountService.getByAccountNumber(accountNumber), HttpStatus.OK);  
//	}
	
	@GetMapping("/getByAccountNumber")
	public ResponseEntity<?> searchByAccountNumber(@RequestParam("accountNumber") String accountNumber){
		Optional<Accounts> accountnum = accountService.getByAccountNumber(accountNumber);
		
		if(accountnum.isPresent()) {
			return ResponseEntity.ok(accountnum.get());
		}
		else {
			return ResponseEntity.status(404).body("No active Account found with this account Number");
					}
		
		  
	}
	
	@GetMapping("/getByAccountHolderNamer")
	public ResponseEntity<?> searchByAccountHolderName(@RequestParam("accountHolderName") String accountHolderName){
		List<Accounts> holderName = accountService.getByAccountHolderName(accountHolderName);
		
		if(holderName.isEmpty()) {
			return ResponseEntity.status(404).body("No Account found with this Name");
		}
		else {
			return ResponseEntity.ok(holderName);
					}
		
		  
	}
	
	@GetMapping("/getaccountbalance")
	public ResponseEntity<BigDecimal> searchByAccountBalance(@RequestParam("accountNumber") String accountNumber, @RequestParam("accountType") String accountType){
		BigDecimal holderName = accountService.getAccountBalance(accountNumber, accountType);
		
			return ResponseEntity.ok(holderName);
		  
	}
	

	
}
