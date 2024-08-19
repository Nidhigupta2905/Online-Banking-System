package in.sp.main.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import in.sp.main.dto.AccountDto;
import in.sp.main.entity.Account;
import in.sp.main.mapper.AccountMapper;
import in.sp.main.repository.AccountRepository;

@Service
public class AccountServiceimpl implements AccountService
{
	
	private AccountRepository accountRepository;
	public AccountServiceimpl(AccountRepository accountRepository)
	{
		this.accountRepository=accountRepository;
	}

	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account account=AccountMapper.mapToAccount(accountDto); 
		Account savedAccount=accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
		
	}

	@Override
	public AccountDto getAccountbyId(Long id) {
		  Account account=accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account does not exist"));
		return AccountMapper.mapToAccountDto(account);
	}

	@Override
    public AccountDto deposit(Long id, double amount) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));
        
        double newBalance = account.getBalance() + amount;
        account.setBalance(newBalance);
        Account updatedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(updatedAccount);
    }
	 public AccountDto withdraw(Long id, double amount) {
	        Account account = accountRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Account does not exist"));
	        
	        if (account.getBalance() < amount) {
	            throw new RuntimeException("Insufficient funds");
	        }
	        
	        double newBalance = account.getBalance() - amount;
	        account.setBalance(newBalance);
	        Account updatedAccount = accountRepository.save(account);
	        return AccountMapper.mapToAccountDto(updatedAccount);
	    }

	    @Override
	    public List<AccountDto> getAllAccounts() {
	        List<Account> accounts = accountRepository.findAll();
	        return accounts.stream()
	                .map(AccountMapper::mapToAccountDto)
	                .collect(Collectors.toList());
	    }

	    @Override
	    public void deleteAccount(Long id) {
	        Account account = accountRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Account does not exist"));
	        accountRepository.deleteById(id);
	    }
	}



