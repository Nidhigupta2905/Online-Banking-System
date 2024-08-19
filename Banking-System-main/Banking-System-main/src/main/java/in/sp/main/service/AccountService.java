package in.sp.main.service;

import java.util.List;

import in.sp.main.dto.AccountDto;

public interface AccountService
{
	AccountDto createAccount(AccountDto accountDto);
	AccountDto getAccountbyId(Long id);
	AccountDto deposit(Long id, double amount);
	AccountDto withdraw(Long id, double amount);
	 List<AccountDto> getAllAccounts();

	    void deleteAccount(Long id);
	}

