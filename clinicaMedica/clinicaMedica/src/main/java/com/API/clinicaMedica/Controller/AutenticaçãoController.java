
 package com.API.clinicaMedica.Controller;

import jakarta.validation.Valid;

import com.API.clinicaMedica.DTOs.UsuarioDTO;
import com.API.clinicaMedica.security.TokenService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("auth")
public class AutenticaçãoController {

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated UsuarioDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.cpf(), data.email(), data.senha());
        var auth = 
    
        
        
    }
    
}
    
