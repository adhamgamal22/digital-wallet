package com.digitalwallet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InsufficientBalanceException extends RuntimeException {

    public InsufficientBalanceException(String message) {
        super(message);
    }

    public InsufficientBalanceException(String walletId, String required, String available) {
        super(String.format("Insufficient balance in wallet %s. Required: %s, Available: %s",
                walletId, required, available));
    }
}