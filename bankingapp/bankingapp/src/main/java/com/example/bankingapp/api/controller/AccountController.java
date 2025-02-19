package com.example.bankingapp.api.controller;

import com.example.bankingapp.dto.request.AccountRequest;
import com.example.bankingapp.dto.response.AccountResponse;
import com.example.bankingapp.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

/*
Missing input validation annotations (@Valid, @NotNull, etc.)
Inconsistent response types (some methods return List<AccountResponse> when they should return single AccountResponse)
Missing documentation for several @Operation annotations
Parameter validation is delegated entirely to service layer
No pagination for getAllAccounts() which could cause performance issues
 */
@RestController
@RequestMapping("/api/v1/accounts") // define base URL for the controller
@RequiredArgsConstructor
@Tag(name = "Account Management", description = "APIs for managing bank accounts")
public class AccountController {

    private final AccountService accountService;

    @GetMapping // map HTTP GET reqs to a med
    @Operation(summary = "Get all accounts", description = "Retrieves a list of all bank accounts")
    public ResponseEntity<List<AccountResponse>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccount());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get account by ID", description = "Retrieves a specific bank account by its ID")
    public ResponseEntity<AccountResponse> getAccountById(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    @PostMapping
    @Operation(summary = "Create new account", description = "Creates a new bank account")
    public ResponseEntity<AccountResponse> createAccount(
            @RequestBody AccountRequest request
            // BIND json req payload to JAVA objects
    ) {
        return new ResponseEntity<>(accountService.createAccount(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update account", description = "Updates an existing bank account")
    public ResponseEntity<AccountResponse> updateAccount(
            @PathVariable Long id, // @PathVar to extract variables from the URL
            @RequestBody AccountRequest request) {
        return ResponseEntity.ok(accountService.updateAccount(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete account", description = "Deletes an existing bank account")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/deposit")
    @Operation(summary = "Deposit money", description = "Deposits money into an account")
    public ResponseEntity<AccountResponse> deposit(
            @PathVariable Long id,
            @RequestParam double amount) {
        return ResponseEntity.ok(accountService.deposit(id, amount));
    }

    @PostMapping("/{id}/withdraw")
    @Operation(summary = "Withdraw money", description = "Withdraws money from an account")
    public ResponseEntity<AccountResponse> withdraw(
            @PathVariable Long id,
            @RequestParam double amount) {
        return ResponseEntity.ok(accountService.withdraw(id, amount));
    }
}

/*
Handles HTTP requests
Maps URLs to service methods
Uses DTOs (AccountRequest/Response) for data transfer
Exposed through Swagger UI for testing
 */