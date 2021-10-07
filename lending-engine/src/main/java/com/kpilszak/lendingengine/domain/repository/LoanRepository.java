package com.kpilszak.lendingengine.domain.repository;

import com.kpilszak.lendingengine.domain.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}
