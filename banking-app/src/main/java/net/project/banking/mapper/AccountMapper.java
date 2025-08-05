//package net.project.banking.mapper;
//
//import net.project.banking.dto.AccountDto;
//import net.project.banking.model.Accounts;
//
//public class AccountMapper {
//	
//	public static Accounts mapToAccount(AccountDto accountDto) {
//		Accounts account = new Accounts(
//				accountDto.getaccountId(),
//				accountDto.getAccountHolderName(),
//				accountDto.getBalance()
//				);
//		return account;
//	}
//	
//	public static AccountDto mapToAccountDto(Accounts account) {
//		AccountDto accountDto = new AccountDto(
//				account.getaccountId(),
//				account.getAccountHolderName(),
//				account.getBalance()
//				);
//		return accountDto;
//	}
//
//}
