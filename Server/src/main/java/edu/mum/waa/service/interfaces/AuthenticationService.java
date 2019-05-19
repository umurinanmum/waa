package edu.mum.waa.service.interfaces;

import edu.mum.waa.dto.LoginDto;
import edu.mum.waa.entity.Credential;

public interface AuthenticationService {
    String login(LoginDto loginDto);
    boolean logOut();
}
