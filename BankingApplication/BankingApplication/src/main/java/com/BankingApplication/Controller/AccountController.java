package com.BankingApplication.Controller;

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

import com.BankingApplication.dto.AccountDto;
import com.BankingApplication.service.AccountService;

@RestController
@RequestMapping("/api/account")
public class AccountController {

	
	private AccountService accountService;
	
	
	// Constructor Injection
	public AccountController(AccountService accountService) {
		super();
		this.accountService = accountService;
	}
	
	

	//Add Account Rest API 
	@PostMapping
	public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto) {
		
		return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
	}
	
	
	
	// Get Account By Id Rest API
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id) {
		
		AccountDto accountDto = accountService.getAccountById(id);
		
		return ResponseEntity.ok(accountDto);
	}
	
	
	// deposit amount rest APi
	@PutMapping("/{id}/deposite")
	public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestBody Map<String, Double> request) {
		
		AccountDto accountDto = accountService.deposit(id, request.get("amount"));
		
		return ResponseEntity.ok(accountDto);
	}
	
	
	// Withdraw Amount Rest API.
	@PutMapping("/{id}/withdraw")
	public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request) {
		
		
		Double amount = request.get("amount");
		AccountDto accountDto = accountService.withdraw(id, amount);
		
		return ResponseEntity.ok(accountDto);
		
	}
	
	
	// To Get All Accounts Rest API.
	@GetMapping
	public ResponseEntity<List<AccountDto>> getAllAccounts() {
		
		List<AccountDto> accountDto = accountService.getAllAccounts();
		
		return ResponseEntity.ok(accountDto);
	}
	
	// Delete Rest APi.
	@DeleteMapping("/{id}/")
	public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
		
		accountService.deleteAccount(id);
		
		return ResponseEntity.ok("Account Deleted Successfully !!!");
	}
	
	
	
	
	
	
	
	
	
}