package com.github.copilot.loanapi;

import com.github.copilot.loanapi.model.Loan;
import com.github.copilot.loanapi.model.User;
import com.github.copilot.loanapi.repository.LoanRepository;
import com.github.copilot.loanapi.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LoanapiApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoanRepository loanRepository;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testGetAllLoans() throws Exception {
        when(loanRepository.findAll()).thenReturn(Arrays.asList(new Loan(), new Loan()));
        mockMvc.perform(get("/api/loans")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetLoanById() throws Exception {
        when(loanRepository.findById(1L)).thenReturn(Optional.of(new Loan()));
        mockMvc.perform(get("/api/loans/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllUsers() throws Exception {
        when(userRepository.findAll()).thenReturn(Arrays.asList(new User(), new User()));
        mockMvc.perform(get("/api/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetUserById() throws Exception {
        when(userRepository.findById(1L)).thenReturn(Optional.of(new User()));
        mockMvc.perform(get("/api/users/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}