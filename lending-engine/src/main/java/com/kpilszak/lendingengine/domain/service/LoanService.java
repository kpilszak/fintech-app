package com.kpilszak.lendingengine.domain.service;

import com.kpilszak.lendingengine.domain.exception.LoanApplicationNotFoundException;
import com.kpilszak.lendingengine.domain.exception.UserNotFoundException;
import com.kpilszak.lendingengine.domain.model.Loan;
import com.kpilszak.lendingengine.domain.model.LoanApplication;
import com.kpilszak.lendingengine.domain.model.User;
import com.kpilszak.lendingengine.domain.repository.LoanApplicationRepository;
import com.kpilszak.lendingengine.domain.repository.LoanRepository;
import com.kpilszak.lendingengine.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoanService {
	
	private final LoanApplicationRepository loanApplicationRepository;
	private final UserRepository userRepository;
	private final LoanRepository loanRepository;
	
	@Autowired
	public LoanService(
			LoanApplicationRepository loanApplicationRepository,
			UserRepository userRepository,
			LoanRepository loanRepository
	) {
		this.loanApplicationRepository = loanApplicationRepository;
		this.userRepository = userRepository;
		this.loanRepository = loanRepository;
	}
	
	public void acceptLoan(final long loanApplicationId, final String lenderUsername) {
		User lender = userRepository.findById(lenderUsername)
		                            .orElseThrow(() -> new UserNotFoundException(lenderUsername));
		LoanApplication loanApplication = loanApplicationRepository.findById(loanApplicationId)
		                                                           .orElseThrow(() -> new LoanApplicationNotFoundException(loanApplicationId));
		loanRepository.save(new Loan(lender, loanApplication));
	}
	
	public List<Loan> getLoans() {
		return loanRepository.findAll();
	}
}
