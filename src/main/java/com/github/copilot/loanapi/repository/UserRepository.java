package com.github.copilot.loanapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.copilot.loanapi.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
