package com.srv.springbootBankingapp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.srv.springbootBankingapp.dto.AccountDto;
import com.srv.springbootBankingapp.service.AccountService;

@RestController

@RequestMapping("/accounts")
public class AccountController {

	private AccountService accountService;

	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	// Add Account Rest API
	@PostMapping
	public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto) {

		return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
	}

	// Get Account Rest API
	@GetMapping("{id}")
	public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id) {
		AccountDto accountDto = accountService.getAccountById(id);
		return ResponseEntity.ok(accountDto);
	}

	// Deposit Account Rest API
	@PutMapping("{id}/deposit")
	public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestBody Map<String, Double> request) {
		Double amount = request.get("amount");
		AccountDto accountDto = accountService.deposit(id, amount);
		return ResponseEntity.ok(accountDto);
	}

	// Withdraw Account Rest API
	@PutMapping("{id}/withdraw")
	public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request) {
		Double amount = request.get("amount");
		AccountDto accountDto = accountService.withdraw(id, amount);
		return ResponseEntity.ok(accountDto);
	}
	
	
	// Get All Account Rest API
	@GetMapping
	public ResponseEntity<List<AccountDto>> getAllAccounts(){
		
		List<AccountDto> accountsDto=accountService.getAllAccounts();
		return ResponseEntity.ok(accountsDto);
	}
	
	// Delete Account Rest API
	@DeleteMapping("{id}")
		public ResponseEntity<String> deleteAccount(@PathVariable Long id){
			
			accountService.deleteAccount(id);
			return ResponseEntity.ok("Account is deleted successfully !");
		}
}
	
	