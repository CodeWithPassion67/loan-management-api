package com.github.copilot.loanapi.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.github.copilot.loanapi.model.User;
import com.github.copilot.loanapi.repository.UserRepository;

import java.util.List;
import java.util.Optional;

/**
 * The UserController class handles HTTP requests related to user management.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /**
     * Creates a new user.
     *
     * @param user The user object to be created.
     * @return The created user object.
     */
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    /**
     * Updates an existing user.
     *
     * @param id           The ID of the user to be updated.
     * @param userDetails The updated user details.
     * @return The updated user object.
     * @throws ResourceNotFoundException If the user is not found.
     */
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setUsername(userDetails.getUsername());
        user.setLoans(userDetails.getLoans());
        return userRepository.save(user);
    }

    /**
     * Retrieves all users.
     *
     * @return A list of all users.
     */
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Retrieves a user by ID.
     *
     * @param id The ID of the user to retrieve.
     * @return An optional containing the user object, or empty if not found.
     */
    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userRepository.findById(id);
    }

    /**
     * Deletes a user by ID.
     *
     * @param id The ID of the user to delete.
     */
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        }
    }

    /**
     * Handles the exception when a resource is not found.
     *
     * @param ex The exception object.
     * @return A ResponseEntity with an error message.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {
        // Handle the exception and return a response
    }
}
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}