package com.API.clinicaMedica.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import com.API.clinicaMedica.Repository.UsuarioRepository;
public class UsuarioDetailsService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    
    
    @Override
    public UserDetails loadUserByUsername(String identificadorU) throws UsernameNotFoundException {
      if(identificadorU.matches("\\d{11}")){
        return UsuarioRepository.findByCpf(identificadorU) 
        .orElseThrow(() -> new UsernameNotFoundException(
            "Médico não encontrado com CPF: " + identificadorU
        ));
      } else {
        return UsuarioRepository.findByEmail(identificadorU)
        .orElseThrow(() -> new UsernameNotFoundException(
            "Paciente não encontrado com o email:" + identificadorU
            ));
      }
        
    }

}
