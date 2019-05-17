package edu.mum.waa.exceptions;

import org.springframework.security.core.AuthenticationException;

public class WaaAuthorizationException extends AuthenticationException {
    private String message;

    public WaaAuthorizationException(String msg) {
        super(msg);
        this.message=msg;
    }


    @Override
    public String getMessage() {
        return message;
    }
}
