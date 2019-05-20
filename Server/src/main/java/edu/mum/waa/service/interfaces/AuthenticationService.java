package edu.mum.waa.service.interfaces;

import edu.mum.waa.dto.LoginDto;
import edu.mum.waa.entity.Credential;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public interface AuthenticationService {
    String login(LoginDto loginDto);
    boolean logOut();
    List<String> getAuthorities();
}
