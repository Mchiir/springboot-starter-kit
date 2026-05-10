package com.mchiir.starterkit.exception;

public class DuplicateUserException extends BusinessException {
    public DuplicateUserException(String message) {
        super(message);
    }
}