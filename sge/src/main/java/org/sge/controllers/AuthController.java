package org.sge.controllers;

import org.sge.dtos.LoginRequestDTO;
import org.sge.dtos.RegisterRequestDTO;
import org.sge.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequestDTO dto){
        authService.register(dto);
    }

    @PostMapping("/login")
    public void login(@RequestBody LoginRequestDTO dto){
        authService.login(dto);
    }
}
