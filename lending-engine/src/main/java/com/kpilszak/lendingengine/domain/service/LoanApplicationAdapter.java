package com.kpilszak.lendingengine.domain.service;

import com.kpilszak.lendingengine.application.model.LoanRequest;
import com.kpilszak.lendingengine.domain.exception.UserNotFoundException;
import com.kpilszak.lendingengine.domain.model.LoanApplication;
import com.kpilszak.lendingengine.domain.model.User;
import com.kpilszak.lendingengine.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Optional;

@Component
public class LoanApplicationAdapter {
	
	private final UserRepository userRepository;
	
	@Autowired
	public LoanApplicationAdapter(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public LoanApplication transform(LoanRequest req, User borrower) {
		Optional<User> userOptional = userRepository.findById(borrower.getUsername());
		
		if (userOptional.isPresent()) {
			return new LoanApplication(req.getAmount(), userOptional.get(), req.getDaysToRepay(),
			                           req.getInterestRate());
			
		} else {
			throw new UserNotFoundException(borrower.getUsername());
		}
	}
}
