package com.pvh.group3_spring_mini_project001.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(ValidatePassword.class)
    public ProblemDetail validatePassword(ValidatePassword p) {
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                p.getMessage()

        );
        detail.setTitle("Invalid Password");
        return detail;
    }
    //email
    @ExceptionHandler(ValidateEmail.class)
    public ProblemDetail  validateEmail(ValidateEmail e){
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                e.getMessage()
        );
        detail.setTitle("Invalid Email please check again");
        return detail;
    }
    //category & task empty validate
    @ExceptionHandler(EmptyValidate.class)
    ProblemDetail emptyValidate(EmptyValidate empty){
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                empty.getMessage()
        );
        detail.setTitle("Field empty");
        return detail;
    }
    //Search by id Validate not found
    @ExceptionHandler(NotFoundValidate.class)
    ProblemDetail notFoundValidate(NotFoundValidate notFound){
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                notFound.getMessage()
        );
        detail.setTitle("NOT FOUND");
        return detail;
    }
    @ExceptionHandler(DuplicateEmail.class)
    ProblemDetail duplicateEmail(DuplicateEmail duplicateEmail){
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                duplicateEmail.getMessage()
        );
        detail.setTitle("Email already Exist");
        return detail;
    }

    @ExceptionHandler(NotYourOwn.class)
    ProblemDetail duplicateEmail(NotYourOwn e){
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                e.getMessage()
        );
        detail.setTitle("Not Your Own");
        return detail;
    }

}
