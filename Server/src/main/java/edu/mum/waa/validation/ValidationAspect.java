package edu.mum.waa.validation;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;

import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
public class ValidationAspect {


    @Pointcut("@annotation(WaaValidate)")
    public void annotation() {
    }

    @Pointcut("args(object)")
    public void args(Object object) {
    }

    @Before("annotation() && args(object)")
    public void doValidate(Object object) {

//        Errors errors = new BeanPropertyBindingResult(object, object.getClass().getName());
//
//        validator.validate(object, errors);
//
//        if (errors.hasErrors()) {
//            List<FieldError> fieldErrors = errors.getFieldErrors();
//            List<String> messages = new ArrayList<String>();
//            for (FieldError fieldError : fieldErrors) {
//                messages.add(messageAccessor.getMessage(fieldError));
//
//            }
//            throw new EaValidationException(errors, messages);
//        }
//
//        return;
    }
}
