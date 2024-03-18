package com.github.copilot.loanapi.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.github.copilot.loanapi.model.Loan;
import com.github.copilot.loanapi.model.User;
import com.github.copilot.loanapi.repository.LoanRepository;
import com.github.copilot.loanapi.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public Loan createLoan(@RequestBody Loan loan) {
        return loanRepository.save(loan);
    }

    @PutMapping("/{id}")
    public Loan updateLoan(@PathVariable Long id, @RequestBody Loan loanDetails) {
        Loan loan = loanRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Loan not found"));
        loan.setAmount(loanDetails.getAmount());
        loan.setTenure(loanDetails.getTenure());
        loan.setUser(loanDetails.getUser());
        return loanRepository.save(loan);
    }

    @GetMapping
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Loan> getLoanById(@PathVariable Long id) {
        return loanRepository.findById(id);
    }

    @DeleteMapping("/{userId}")
    public void removeLoanForUser(@PathVariable Long userId) {
        Optional<User> user = userRepository.findById(userId);
        List<Loan> loans = user.get().getLoans();
        loanRepository.deleteAll(loans);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}