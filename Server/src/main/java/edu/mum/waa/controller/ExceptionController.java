package edu.mum.waa.controller;

import edu.mum.waa.dto.DomainError;
import edu.mum.waa.dto.DomainErrors;
import edu.mum.waa.exceptions.StudentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ControllerAdvice
public class ExceptionController {


    @Autowired
    MessageSourceAccessor messageAccessor;

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public DomainErrors handleException(MethodArgumentNotValidException exception) {
        System.out.println("handling exception ====");
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        DomainErrors errors = new DomainErrors();
        errors.setErrorType("ValidationError");
        for (FieldError fieldError : fieldErrors) {
            DomainError error = new DomainError(messageAccessor.getMessage(fieldError));
            errors.addError(error);
        }

        return errors;
    }

    @ExceptionHandler({StudentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public DomainErrors handleException2(StudentException exception) {
        System.out.println("handling StudentException ====");

        DomainErrors errors = new DomainErrors();
        errors.setErrorType("ValidationError");



        DomainError error = new DomainError(messageAccessor.getMessage(exception.getMessage()));
        errors.addError(error);

        return errors;
    }

}