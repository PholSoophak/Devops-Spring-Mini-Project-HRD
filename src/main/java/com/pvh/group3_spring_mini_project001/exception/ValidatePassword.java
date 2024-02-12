package com.pvh.group3_spring_mini_project001.exception;

public class ValidatePassword extends RuntimeException {
    public ValidatePassword(String message) {
        super(message);
    }
}