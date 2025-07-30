package com.API.clinicaMedica.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.API.clinicaMedica.Repository.UsuarioRepository;


@Service
@EnableWebSecurity
public class UsuarioDetailsService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    
    
    @Override
    public  UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
      if(login.matches("\\d{11}")) { // Verifica se é um CPF
        return UsuarioRepository.findByCpf(login) 
            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com CPF: " + login));
          
      } else { // Assume que é um email
        return UsuarioRepository.findByEmail(login)
            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com email: " + login));
        
    }

}
}
