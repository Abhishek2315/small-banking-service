package net.project.banking.repo;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.project.banking.dto.AccountDto;
import net.project.banking.model.Accounts;

@Repository
public interface AccountRepo extends JpaRepository<Accounts , Long> {

//	AccountDto save(AccountDto accountDto);
	
	Optional<Accounts> findByAccountNumber(String accountNumber);
	
	public Optional<Accounts> findByAccountNumberAndStatus(String accountNumber, String status);
	Accounts findByAccountNumberAndAccountTypeAndStatus(String accountNumber,String accountType, String status);
		

	List<Accounts> findByAccountHolderName(String accountHolderName);	
	
	Accounts findByAccountNumberAndAccountType(String accountNumber, String accountType);

	Accounts findByAccountId(long accountId);
	
//	Accounts findByAccountNumberAndStatus(String accountNumber, String status);
	
}
