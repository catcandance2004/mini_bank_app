package com.example.bankingapp.exception;

public class UnbalanceException extends RuntimeException{
    public UnbalanceException(String message){
        super(message);
    }
}
