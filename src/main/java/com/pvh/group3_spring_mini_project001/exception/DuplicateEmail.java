package com.pvh.group3_spring_mini_project001.exception;

public class DuplicateEmail extends RuntimeException{
    public DuplicateEmail(String message) {
        super(message);
    }
}
