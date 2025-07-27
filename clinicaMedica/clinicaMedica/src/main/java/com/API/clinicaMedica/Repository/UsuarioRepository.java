package com.API.clinicaMedica.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.API.clinicaMedica.Model.UsuarioModel;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, String> {
    Optional<UsuarioModel> findByEmail(String email);
    Optional<UsuarioModel> findByCpf(String cpf);

}
