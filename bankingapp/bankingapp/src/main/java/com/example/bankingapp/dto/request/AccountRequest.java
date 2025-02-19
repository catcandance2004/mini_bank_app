package com.example.bankingapp.dto.request;

import lombok.Data;

@Data
public class AccountRequest {
    private String name;
    private String email;
    private double balance;
}
