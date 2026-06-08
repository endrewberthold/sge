package org.sge.auth.controller;

import org.sge.auth.dto.AuthResponseDTO;
import org.sge.auth.dto.LoginRequestDTO;
import org.sge.auth.dto.RegisterRequestDTO;
import org.sge.auth.service.AuthService;
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
    public AuthResponseDTO login(@RequestBody LoginRequestDTO dto){
        return authService.login(dto);
    }
}
