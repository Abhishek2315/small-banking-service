package net.project.banking.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.project.banking.model.Accounts;
import net.project.banking.model.Transaction;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction , Long> {

//	List<Transaction> findByAccount_Id(Long accountId);

	List<Transaction> findByAccountId(long l);

//	List<Transaction> findByAccount_Id(long l);

}
