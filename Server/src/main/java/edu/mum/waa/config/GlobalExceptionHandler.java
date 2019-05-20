package edu.mum.waa.config;

import edu.mum.waa.exceptions.WaaAuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler({WaaAuthorizationException.class})
    public String denied(Exception e) {
       return "403";
    }


    @ExceptionHandler({Exception.class})
    public String doIt(Exception e) {
        e.printStackTrace();
        return "redirect:/authorization/login";
    }

}
