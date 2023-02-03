package com.example.shop_accounts_system.exception_handling;

public class BalanceInsufficientException extends Exception{
    public BalanceInsufficientException(String message){
        super(message);
    }
}
