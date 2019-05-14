package edu.mum.waa.service;

import edu.mum.waa.dto.LoginDto;
import edu.mum.waa.entity.Credential;
import edu.mum.waa.repository.AuthenticationRepo;
import edu.mum.waa.security.JwtUserDetails;
import edu.mum.waa.security.JwtUtil;
import edu.mum.waa.service.interfaces.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private AuthenticationRepo authenticationRepo;
    private JwtUtil jwtUtil;

    @Autowired
    public AuthenticationServiceImpl(AuthenticationRepo authenticationRepo, JwtUtil jwtUtil) {
        this.authenticationRepo = authenticationRepo;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public String login(LoginDto loginDto) {
        Credential credential = authenticationRepo.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());

        if (credential != null) {
// generate token
            JwtUserDetails jwtUserDetails = new JwtUserDetails();
            jwtUserDetails.setUsername(loginDto.getEmail());
            //TODO student or faculty id
            jwtUserDetails.setId(credential.getId());

            //TODO fetch roles

            return jwtUtil.encode(jwtUserDetails);
        }

        return null;
    }
}
