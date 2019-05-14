package edu.mum.client.controller;

import edu.mum.client.model.LoginModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/authorization")
public class AuthorizationController {


    @GetMapping
    public String showLoginForm(@ModelAttribute LoginModel loginModel){

        return "login";
    }

    @PostMapping("/do-login")
    public String doLogin(@ModelAttribute LoginModel loginModel){



        return "welcome";
    }

}
