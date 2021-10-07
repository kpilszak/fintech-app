package com.kpilszak.lendingengine.application;

import com.kpilszak.lendingengine.application.model.LoanRequest;
import com.kpilszak.lendingengine.application.service.TokenValidationService;
import com.kpilszak.lendingengine.domain.model.Loan;
import com.kpilszak.lendingengine.domain.model.LoanApplication;
import com.kpilszak.lendingengine.domain.model.User;
import com.kpilszak.lendingengine.domain.repository.LoanApplicationRepository;
import com.kpilszak.lendingengine.domain.repository.UserRepository;
import com.kpilszak.lendingengine.domain.service.LoanApplicationAdapter;
import com.kpilszak.lendingengine.domain.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class LoanController {
	
	private final LoanApplicationRepository loanApplicationRepository;
	private final UserRepository userRepository;
	private final LoanApplicationAdapter loanApplicationAdapter;
	private final LoanService loanService;
	private final TokenValidationService tokenValidationService;
	
	@Autowired
	public LoanController(
			LoanApplicationRepository loanApplicationRepository,
			UserRepository userRepository,
			LoanApplicationAdapter loanApplicationAdapter,
			LoanService loanService,
			TokenValidationService tokenValidationService
	) {
		this.loanApplicationRepository = loanApplicationRepository;
		this.userRepository = userRepository;
		this.loanApplicationAdapter = loanApplicationAdapter;
		this.loanService = loanService;
		this.tokenValidationService = tokenValidationService;
	}
	
	@PostMapping("/loan/request")
	public void requestLoan(@RequestBody final LoanRequest loanRequest, HttpServletRequest request) {
		User borrower = tokenValidationService.validateTokenAndGetUser(request.getHeader(HttpHeaders.AUTHORIZATION));
		loanApplicationRepository.save(loanApplicationAdapter.transform(loanRequest, borrower));
	}
	
	@GetMapping("/loan/requests")
	public List<LoanApplication> findAllLoanApplication(HttpServletRequest request) {
		tokenValidationService.validateTokenAndGetUser(request.getHeader(HttpHeaders.AUTHORIZATION));
		return loanApplicationRepository.findAll();
	}
	
	@GetMapping("/users")
	public List<User> findUsers(HttpServletRequest request) {
		tokenValidationService.validateTokenAndGetUser(request.getHeader(HttpHeaders.AUTHORIZATION));
		return userRepository.findAll();
	}
	
	@PostMapping("/loan/accept/{loanApplicationId}")
	public void acceptLoan(@PathVariable final String loanApplicationId,
			HttpServletRequest request) {
		User lender = tokenValidationService.validateTokenAndGetUser(request.getHeader(HttpHeaders.AUTHORIZATION));
		loanService.acceptLoan(Long.parseLong(loanApplicationId), lender.getUsername());
	}
	
	@GetMapping("/loans")
	public List<Loan> getLoans(HttpServletRequest request) {
		tokenValidationService.validateTokenAndGetUser(request.getHeader(HttpHeaders.AUTHORIZATION));
		return loanService.getLoans();
	}
}
