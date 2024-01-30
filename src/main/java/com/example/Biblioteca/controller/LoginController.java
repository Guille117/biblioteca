package com.example.Biblioteca.controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Biblioteca.dto.DtoMUsuario;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
public class LoginController {

    @PostMapping("/login")
    public void login(@Valid @RequestBody DtoMUsuario usu){
    
    }

}