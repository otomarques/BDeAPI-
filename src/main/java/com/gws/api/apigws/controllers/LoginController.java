package com.gws.api.apigws.controllers;

import com.gws.api.apigws.DTOs.LoginDto;
import com.gws.api.apigws.DTOs.TokenDto;
import com.gws.api.apigws.models.UsuarioModel;
import com.gws.api.apigws.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid LoginDto dadosLogin){
        var usernamePassword = new UsernamePasswordAuthenticationToken(dadosLogin.email(), dadosLogin.senha());

        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.gerarToken((UsuarioModel) auth.getPrincipal());

        return ResponseEntity.status(HttpStatus.OK).body(new TokenDto(token));
    }
}