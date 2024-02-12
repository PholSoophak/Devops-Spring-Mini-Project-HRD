package com.pvh.group3_spring_mini_project001.exception;

public class ValidateEmail extends RuntimeException{
    public ValidateEmail(String message) {
        super(message);
    }
}
