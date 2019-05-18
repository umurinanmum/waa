package edu.mum.client.controller;

import edu.mum.client.config.WaaAuthenticationModel;
import edu.mum.client.helper.Constants;
import edu.mum.client.model.LoginModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/authorization")
public class AuthorizationController {


    @GetMapping("/login")
    public String showLoginForm(@ModelAttribute LoginModel loginModel) {

        return "login";
    }

    @PostMapping("/do-login")
    public String doLogin(@ModelAttribute LoginModel loginModel, HttpSession session) {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.postForEntity(Constants.URL_AUTH, loginModel, String.class);
        System.out.println(result.getBody());
        if (result.getBody() != null && !result.getBody().trim().isEmpty()) {
            session.setAttribute("token",result.getBody());
//            WaaAuthenticationModel waaAuthenticationModel = new WaaAuthenticationModel();
//            waaAuthenticationModel.setAuthenticated(true);
//            waaAuthenticationModel.setToken(result.getBody());
//
//            SecurityContextHolder.getContext().setAuthentication(waaAuthenticationModel);
            return "redirect:/welcome";
        }
        return "redirect:/authorization/login";
    }

}
