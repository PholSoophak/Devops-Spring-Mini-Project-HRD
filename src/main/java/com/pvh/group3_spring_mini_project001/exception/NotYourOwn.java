package com.pvh.group3_spring_mini_project001.exception;

public class NotYourOwn extends RuntimeException{
    public NotYourOwn(String message) {
        super(message);
    }
}
