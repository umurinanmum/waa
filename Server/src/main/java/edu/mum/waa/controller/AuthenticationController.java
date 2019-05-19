package edu.mum.waa.controller;

import edu.mum.waa.dto.LoginDto;
import edu.mum.waa.service.interfaces.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/authentication")
public class AuthenticationController {

    private AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public String login(@RequestBody LoginDto loginDto) {
        return authenticationService.login(loginDto);
    }

    @DeleteMapping
    public boolean login(@RequestParam String token)
    {
        return authenticationService.logOut();
    }

}
