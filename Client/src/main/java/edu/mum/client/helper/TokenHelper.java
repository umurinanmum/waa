package edu.mum.client.helper;

import edu.mum.client.config.WaaAuthenticationModel;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class TokenHelper {

    @Autowired
    HttpSession httpSession;

    public String getToken() {
        return httpSession.getAttribute("token").toString();
    }

    public void deleteToken(){
        httpSession.removeAttribute("token");
        SecurityContextHolder.clearContext();
    }

}
