package com.srv.springbootBankingapp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.srv.springbootBankingapp.dto.AccountDto;
import com.srv.springbootBankingapp.entities.Account;
import com.srv.springbootBankingapp.mapper.AccountMapper;
import com.srv.springbootBankingapp.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService{
	
	private AccountRepository accountRepository;
	
	public  AccountServiceImpl (AccountRepository accountRepository){
		this.accountRepository=accountRepository;
	}
	
	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account account=AccountMapper.mapToAccount(accountDto);
		Account savedAccount=accountRepository.save(account);
		
		return AccountMapper.mapToAccountDto(savedAccount);
		
	}

	@Override
	public AccountDto getAccountById(Long id) {
		Account account=accountRepository
				.findById(id)
				.orElseThrow(()->new RuntimeException("Account does not exist"));
		return AccountMapper.mapToAccountDto(account);
	}

	@Override
	public AccountDto deposit(Long id, double amount) {
		Account account=accountRepository
				.findById(id)
				.orElseThrow(()->new RuntimeException("Account does not exist"));
		
		double total=account.getBalance()+amount;
		account.setBalance(total);
		Account savedAccount=accountRepository.save(account);
		
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto withdraw(Long id, double amount) {
		
		Account account=accountRepository
				.findById(id)
				.orElseThrow(()->new RuntimeException("Account does not exist"));
		
		if(account.getBalance()<amount) {
			throw new RuntimeException("Insufficient amount");
		}
		
		double total=account.getBalance()-amount;
		account.setBalance(total);
		Account savedAccount=accountRepository.save(account);
		
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public List<AccountDto> getAllAccounts() {
		List<Account>accounts=accountRepository.findAll();
		return accounts.stream()
				.map((account)->AccountMapper.mapToAccountDto(account))
				.collect(Collectors.toList());
	}

	@Override
	public void deleteAccount(Long id) {
		
		Account account=accountRepository
				.findById(id)
				.orElseThrow(()->new RuntimeException("Account does not exist"));
		  accountRepository.deleteById(id);
	}

}
