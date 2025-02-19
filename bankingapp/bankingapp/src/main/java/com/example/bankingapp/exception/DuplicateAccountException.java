package com.example.bankingapp.exception;

public class DuplicateAccountException extends RuntimeException{
    public DuplicateAccountException(String message) {
        super(message);
    }
}
