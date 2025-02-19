package com.example.bankingapp.service.implement;

import com.example.bankingapp.dto.request.AccountRequest;
import com.example.bankingapp.dto.response.AccountResponse;
import com.example.bankingapp.exception.DuplicateAccountException;
import com.example.bankingapp.domain.model.Account;
import com.example.bankingapp.repository.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.bankingapp.service.AccountService;
import com.example.bankingapp.exception.AccountNotFoundException;
import com.example.bankingapp.exception.UnbalanceException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // anno to gen constructor
@Transactional

public class AccountServiceImplement implements AccountService {
    private final AccountRepository accountRepository;

    @Override
    public List<AccountResponse> getAllAccount() {
        return accountRepository.findAll()
                .stream()
                .map(AccountResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public AccountResponse getAccountById(Long id) {
        Account account = findAccountOrThrow(id);
        return AccountResponse.fromEntity(account);
    }

    @Override
    public AccountResponse createAccount(AccountRequest request) {
        if (accountRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateAccountException("Account have email " + request.getEmail() + "exists");
        }

        Account account = new Account();
        account.setName(request.getName());
        account.setEmail(request.getEmail());
        account.setBalance(request.getBalance());

        Account savedAccount = accountRepository.save(account);
        return AccountResponse.fromEntity(savedAccount);
    }

    @Override
    public AccountResponse updateAccount(Long id, AccountRequest request) {
        Account account = findAccountOrThrow(id);

        account.setName(request.getName());
        account.setEmail(request.getEmail());

        Account updatedAccount = accountRepository.save(account);
        return AccountResponse.fromEntity(updatedAccount);
    }

    @Override
    public void deleteAccount(Long id) {
        if (!accountRepository.existsById(id)){
            throw new AccountNotFoundException("Account not found of id " + id);
        }
        accountRepository.deleteById(id);
    }
/*
Logic for crud operations
 */


    @Override
    @Transactional // ensure db operations atomic
    public AccountResponse deposit(Long id, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit needs > 0");
        }
        Account account = findAccountOrThrow(id);
        account.setBalance(account.getBalance() + amount);
        Account updatedAccount = accountRepository.save(account);
        return AccountResponse.fromEntity(updatedAccount);

    }

    @Override //med to handle withdrawal circumstance
    public AccountResponse withdraw(Long id, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit needs > 0");
        }
        Account account = findAccountOrThrow(id);
        if (account.getBalance() < amount) {
            throw new UnbalanceException("Unbalance to withdraw");
        }
        account.setBalance(account.getBalance() - amount);
        Account updatedAccount = accountRepository.save(account);
        return AccountResponse.fromEntity(updatedAccount);
    }
/*
transaction handle
 */


    // med to reduce code duplication
    private Account findAccountOrThrow(Long id){
        return accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found of id " + id));
    }
}

/*
throw exceptions when rules are violated
 */