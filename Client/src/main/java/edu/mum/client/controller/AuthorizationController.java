package edu.mum.client.controller;

import edu.mum.client.config.WaaAuthenticationModel;
import edu.mum.client.helper.Constants;
import edu.mum.client.helper.TokenHelper;
import edu.mum.client.model.BlockReportModel;
import edu.mum.client.model.LoginModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/authorization")
public class AuthorizationController {


    @Autowired
    private TokenHelper tokenHelper;


    @GetMapping("/login")
    public String showLoginForm(@ModelAttribute LoginModel loginModel) {
        return "login";
    }

    @GetMapping("/logout")
    public String logOut(@ModelAttribute LoginModel loginModel) {

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(Constants.URL_AUTH + "?token=123");
        tokenHelper.deleteToken();

        return "login";
    }

    @PostMapping("/do-login")
    public String doLogin(@ModelAttribute LoginModel loginModel, HttpSession session) {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.postForEntity(Constants.URL_AUTH, loginModel, String.class);
        System.out.println(result.getBody());
        if (result.getBody() != null && !result.getBody().trim().isEmpty()) {
            session.setAttribute("token", result.getBody());

            WaaAuthenticationModel waaAuthenticationModel = new WaaAuthenticationModel();
            waaAuthenticationModel.setAuthenticated(true);
            waaAuthenticationModel.setToken(result.getBody());


            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + tokenHelper.getToken());

            HttpEntity entity = new HttpEntity(headers);

            ResponseEntity<List<String>> authResponse = restTemplate.exchange(Constants.URL_AUTH,
                    HttpMethod.GET, entity, new ParameterizedTypeReference<List<String>>() {
                    });

            if (authResponse!=null){
                String csvRoles = String.join(",", authResponse.getBody());
                List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(csvRoles);
                waaAuthenticationModel.setAuthorities(grantedAuthorities);
            }

            SecurityContextHolder.getContext().setAuthentication(waaAuthenticationModel);

            return "welcome";

        }
        return "redirect:/authorization/login";
    }

}
