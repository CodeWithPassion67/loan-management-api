package com.github.copilot.loanapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.copilot.loanapi.model.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}