package net.project.banking.serviceImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Authentication;
import org.springframework.stereotype.Service;

import net.project.banking.dto.AccountDto;
//import net.project.banking.mapper.AccountMapper;
import net.project.banking.model.Accounts;
import net.project.banking.repo.AccountRepo;
import net.project.banking.security.JwtHelper;
import net.project.banking.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepo accountRepo;
	
	@Autowired
	private JwtHelper auth;
	
//	@Override
//	public AccountDto createAccount(AccountDto accountDto) {
//		// TODO Auto-generated method stub
////		Accounts account = AccountMapper.mapToAccount(accountDto);
////		Accounts savedAccount = accountRepo.save(account);
//		return AccountMapper.mapToAccountDto(savedAccount);
//	}
	
	@Override
	public Accounts addAccount(Accounts account) {
		// TODO Auto-generated method stub
		account.setAccountNumber(generateUniqueAccountNumber());
		account.setCreatedTime(LocalDateTime.now());
		account.setLastTransaction(LocalDateTime.now());
		account.setStatus("Active");
		return accountRepo.save(account);
	}
	
	private String generateUniqueAccountNumber() {
		Random random = new Random();
		String accountNumber;
		do {
			long dummyAccountNo = 1000000000L + (long)(random.nextDouble() * 9000000000L);// 10-digit Random Number
				accountNumber = String.valueOf(dummyAccountNo);
		}while(accountRepo.findByAccountNumber(accountNumber).isPresent());
		
		return accountNumber;
	}

	@Override
	public Optional<Accounts> getByAccountNumber(String accountNumber) {
		// TODO Auto-generated method stub
			 
		return accountRepo.findByAccountNumberAndStatus(accountNumber, "Active");
	}

	
	
	@Override
	public List<Accounts> getByAccountHolderName(String accountHolderName) {
		// TODO Auto-generated method stub
		return accountRepo.findByAccountHolderName(accountHolderName);
	}

	@Override
	public BigDecimal getAccountBalance(String accountNumber,String accountType) {
		
		Accounts accountBalance = accountRepo.findByAccountNumberAndAccountType(accountNumber, accountType);
			
		
		return accountBalance.getBalance();
	}

	
}
