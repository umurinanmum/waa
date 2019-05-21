package edu.mum.waa.security;


import edu.mum.waa.exceptions.WaaAuthorizationException;
import lombok.var;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Component
@Aspect
public class SecurityInterceptor {

    @Pointcut("@annotation(WaaSecured)")
    public void annotation() {
    }

//    @Pointcut("args(object)")
//    public void args(Object object) {
//    }

    @Before("annotation()")
    public void doAuth(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        WaaSecured myAnnotation = method.getAnnotation(WaaSecured.class);
        String value = myAnnotation.value();

        var roles = ((JwtUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getRoles();

        var result = roles.stream().filter(l -> l.equals(value)).findAny();
        if (!result.isPresent() ||result == null || result.get() == null) {
            throw new WaaAuthorizationException("403");
        }

    }


}
