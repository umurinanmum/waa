package edu.mum.client.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

@ExceptionHandler({Exception.class})
    public String doIt(){
        return "redirect:/authorization/login";
}

}
