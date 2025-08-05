package net.project.banking.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import net.project.banking.dto.AccountDto;
import net.project.banking.model.Accounts;

@Service
public interface AccountService {
	
//	AccountDto createAccount(AccountDto accountDto);
	
//	AccountDto addAccount(AccountDto accountDto);

	Accounts addAccount(Accounts account);
	
	Optional<Accounts> getByAccountNumber(String accountNumber);
	
	

	List<Accounts> getByAccountHolderName(String accountHolderName);

	BigDecimal getAccountBalance(String accountNumber, String accountType);
}
