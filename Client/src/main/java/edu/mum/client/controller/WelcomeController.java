package edu.mum.client.controller;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    @GetMapping("/welcome")
    private String hello(){
        //System.out.println(SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
        return "welcome";
    }

}
