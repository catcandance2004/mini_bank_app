package com.example.bankingapp.dto.response;

import com.example.bankingapp.model.Account;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountResponse {
    private Long id;
    private String name;
    private String email;
    private double balance;

    public static AccountResponse fromEntity(Account account) {
        return AccountResponse.builder()
                .id(account.getId())
                .name(account.getName())
                .email(account.getEmail())
                .balance(account.getBalance())
                .build();
    }
}
