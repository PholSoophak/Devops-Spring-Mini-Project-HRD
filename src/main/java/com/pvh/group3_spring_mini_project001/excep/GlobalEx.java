package com.pvh.group3_spring_mini_project001.excep;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.URI;

@ControllerAdvice
public class GlobalEx {

    @ExceptionHandler
    public ProblemDetail problemDetail(
            EmptyInput emptyInput
    ){
        ProblemDetail problemDetail=ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,emptyInput.getMessage()
        );
          problemDetail.setTitle("Invalid field !!");
          problemDetail.setType(URI.create("localhost:8080/error/not-found"));
          return problemDetail;
    }
}
