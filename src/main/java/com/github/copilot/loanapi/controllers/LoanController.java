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

/**
 * The LoanController class handles HTTP requests related to loans.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.github.copilot.loanapi.exceptions.ResourceNotFoundException;
import com.github.copilot.loanapi.models.Loan;
import com.github.copilot.loanapi.models.User;
import com.github.copilot.loanapi.repositories.LoanRepository;
import com.github.copilot.loanapi.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

/**
 * Controller class for managing loans.
 */
@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Creates a new loan.
     *
     * @param loan The loan object to be created.
     * @return The created loan.
     */
    @PostMapping
    public Loan createLoan(@RequestBody Loan loan) {
        return loanRepository.save(loan);
    }

    /**
     * Updates an existing loan.
     *
     * @param id          The ID of the loan to be updated.
     * @param loanDetails The updated loan details.
     * @return The updated loan.
     * @throws ResourceNotFoundException if the loan is not found.
     */
    @PutMapping("/{id}")
    public Loan updateLoan(@PathVariable Long id, @RequestBody Loan loanDetails) {
        Loan loan = loanRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Loan not found"));
        loan.setAmount(loanDetails.getAmount());
        loan.setTenure(loanDetails.getTenure());
        loan.setUser(loanDetails.getUser());
        return loanRepository.save(loan);
    }

    /**
     * Retrieves all loans.
     *
     * @return A list of all loans.
     */
    @GetMapping
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    /**
     * Retrieves a loan by ID.
     *
     * @param id The ID of the loan to retrieve.
     * @return The loan with the specified ID, if found.
     */
    @GetMapping("/{id}")
    public Optional<Loan> getLoanById(@PathVariable Long id) {
        return loanRepository.findById(id);
    }

    /**
     * Removes all loans for a given user.
     *
     * @param userId The ID of the user whose loans should be removed.
     */
    @DeleteMapping("/{userId}")
    public void removeLoanForUser(@PathVariable Long userId) {
        Optional<User> user = userRepository.findById(userId);
        List<Loan> loans = user.get().getLoans();
        loanRepository.deleteAll(loans);
    }

    /**
     * Exception handler for handling resource not found exceptions.
     *
     * @param ex The resource not found exception.
     * @return A response entity with the error message and HTTP status code.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}