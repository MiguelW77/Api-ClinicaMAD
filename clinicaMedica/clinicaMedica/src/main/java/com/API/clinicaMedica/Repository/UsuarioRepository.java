package com.API.clinicaMedica.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.API.clinicaMedica.User.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Optional<Usuario> findByEmail(String email);
    
       
}
