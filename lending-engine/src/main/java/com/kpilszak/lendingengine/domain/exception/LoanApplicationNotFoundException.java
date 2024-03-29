package com.kpilszak.lendingengine.domain.exception;

public class LoanApplicationNotFoundException extends RuntimeException {
	
	public LoanApplicationNotFoundException(long loanApplicationId) {
		super("Loan application with id: " + loanApplicationId + " not found");
	}
}
