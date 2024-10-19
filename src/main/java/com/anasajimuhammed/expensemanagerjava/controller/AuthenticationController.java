package com.anasajimuhammed.expensemanagerjava.controller;

import com.anasajimuhammed.expensemanagerjava.model.UserDataModel;
import com.anasajimuhammed.expensemanagerjava.services.AuthenticationService;
import com.anasajimuhammed.expensemanagerjava.services.ServiceImpl.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("auth/")
@RestController()
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationService = authenticationService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public UserDataModel registerUser(@RequestBody UserDataModel registerData){
        return authenticationService.registerNewUser(registerData);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDataModel loginData){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginData.getEmail(), loginData.getPassword())
        );
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(loginData.getEmail());
        }
        else{
            return "Login Failed";
        }
    }

}
