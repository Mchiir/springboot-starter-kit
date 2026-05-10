package com.mchiir.starterkit.exception;

public class InvalidCredentialsException extends BusinessException {
    public InvalidCredentialsException() {
        super("Invalid credentials");
    }
}