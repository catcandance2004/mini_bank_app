package com.example.bankingapp.service;

import com.example.bankingapp.dto.request.AccountRequest;
import com.example.bankingapp.dto.response.AccountResponse;

import java.util.List;

public interface AccountService {
    List<AccountResponse> getAllAccount();
    AccountResponse getAccountById(Long id);
    AccountResponse createAccount(AccountRequest request);
    AccountResponse updateAccount(Long id, AccountRequest request);
    void deleteAccount(Long id);
    AccountResponse deposit(Long id, double amount);
    AccountResponse withdraw(Long id, double amount);
}
