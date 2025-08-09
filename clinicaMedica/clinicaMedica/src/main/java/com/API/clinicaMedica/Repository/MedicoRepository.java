package com.API.clinicaMedica.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.API.clinicaMedica.Model.MedicoModel;

@Repository
public interface MedicoRepository extends JpaRepository<MedicoModel, Long> {
     MedicoModel findByEmailAndSenha(String email, String senha);
}
