package edu.mum.waa.config;

import edu.mum.waa.dto.DomainError;
import edu.mum.waa.dto.DomainErrors;
import edu.mum.waa.dto.ValidationErrorDto;
import edu.mum.waa.exceptions.StudentException;
import edu.mum.waa.exceptions.WaaAuthorizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private MessageSourceAccessor messageSourceAccessor;


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public ValidationErrorDto validationError(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        return convertError(fieldErrors);

    }

    @ExceptionHandler({StudentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public DomainErrors handleException2(StudentException exception) {
        //System.out.println("handling StudentException ==== 111");

        DomainErrors errors = new DomainErrors();
        errors.setErrorType("ValidationError");

        DomainError error = new DomainError(messageSourceAccessor.getMessage(exception.getMessage()));
        errors.addError(error);

        return errors;
    }

    @ExceptionHandler({WaaAuthorizationException.class})
    public String denied(Exception e) {
        return "403";
    }


    @ExceptionHandler({Exception.class})
    public String doIt(Exception e) {
        System.out.println("catching general exception .........");
        e.printStackTrace();
        return "redirect:/authorization/login";
    }

    private ValidationErrorDto convertError(List<FieldError> fieldErrors) {
        ValidationErrorDto validationErrorDto = new ValidationErrorDto();
        validationErrorDto.setErrorType("ValidationError");

        for(FieldError fieldError : fieldErrors){
            validationErrorDto.addError(fieldError.getField(),messageSourceAccessor.getMessage(fieldError));
        }

        return validationErrorDto;

    }

}
