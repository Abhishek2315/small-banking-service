package net.project.banking.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.project.banking.model.Credit_and_Debit;

@Repository
public interface Credit_and_DebitRepo extends JpaRepository<Credit_and_Debit , Long> {

}
