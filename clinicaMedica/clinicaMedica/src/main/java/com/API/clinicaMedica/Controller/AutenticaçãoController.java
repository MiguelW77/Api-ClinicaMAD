
 package com.API.clinicaMedica.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.API.clinicaMedica.DTOs.RegistroDTO;
import com.API.clinicaMedica.DTOs.UsuarioDTO;
import com.API.clinicaMedica.Repository.UsuarioRepository;
import com.API.clinicaMedica.User.Usuario;
import com.API.clinicaMedica.security.TokenService;
import com.API.clinicaMedica.DTOs.LoginResponseDTO;

import jakarta.validation.Valid;



@RestController
@RequestMapping("auth")
public class AutenticaçãoController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired 
    private UsuarioRepository repositorio;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UsuarioDTO data ){
        var usernamePassword = new UsernamePasswordAuthenticationToken(
        
        data.email(),
        data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword); 
        var token = tokenService.generateToken((Usuario) auth.getPrincipal());

        
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
    
    @PostMapping("/registro")
    public ResponseEntity registro(@RequestBody @Valid RegistroDTO data){
        if(this.repositorio.findByEmail(data.email()) != null ) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
        Usuario newUser = new Usuario(data.email() , encryptedPassword , data.role());
     return ResponseEntity.ok(this.repositorio.save(newUser));   
    }
}
    
