package edu.mum.waa.service;

import edu.mum.waa.dto.LoginDto;
import edu.mum.waa.entity.Credential;
import edu.mum.waa.exceptions.WaaAuthorizationException;
import edu.mum.waa.repository.AuthenticationRepo;
import edu.mum.waa.security.JwtAuthenticationTokenFilter;
import edu.mum.waa.security.JwtUserDetails;
import edu.mum.waa.security.JwtUtil;
import edu.mum.waa.service.interfaces.AuthenticationService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private AuthenticationRepo authenticationRepo;
    private JwtUtil jwtUtil;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    public AuthenticationServiceImpl(AuthenticationRepo authenticationRepo, JwtUtil jwtUtil) {
        this.authenticationRepo = authenticationRepo;
        this.jwtUtil = jwtUtil;
    }

    public boolean logOut() {
        SecurityContextHolder.clearContext();
        return true;
    }

    public List<String> getAuthorities() {

        String header = httpServletRequest.getHeader("Authorization");
        if (header != null || header.startsWith("Bearer ")) {
            String token = header.split(" ")[1];
             JwtUserDetails jwtUserDetails =jwtUtil.decode(token);
            String csvRoles = String.join(",", jwtUserDetails.getRoles());
            //List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(csvRoles);
            return jwtUserDetails.getRoles();
        }
        return null;
    }

    @Override
    public String login(LoginDto loginDto) {
        Credential credential = authenticationRepo.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());

        if (credential != null) {
// generate token
            JwtUserDetails jwtUserDetails = new JwtUserDetails();
            jwtUserDetails.setUsername(loginDto.getEmail());

            // student or faculty id
            if (credential.getFaculty() != null) {
                jwtUserDetails.setId(credential.getFaculty().getId());
            } else {
                jwtUserDetails.setId(credential.getStudent().getId());
            }

            //fetch roles
            jwtUserDetails.setRoles(credential.getRoles().stream().map(l -> l.getName()).collect(Collectors.toList()));

            return jwtUtil.encode(jwtUserDetails);
        }

        return null;
    }
}
