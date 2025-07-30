package com.API.clinicaMedica.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.API.clinicaMedica.Model.UsuarioModel;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, String> {
    
    static Optional<UsuarioModel> findByCpf (String cpf){
        if (cpf == null || cpf.isEmpty()) {
            return Optional.empty();
        }
        return findByCpf(cpf);
    }
    static Optional<UsuarioModel> findByEmail (String email){
        if (email == null || email.isEmpty()) {
            return Optional.empty();
        }
        return findByEmail(email);  
    
        }
}
