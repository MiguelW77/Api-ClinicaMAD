package com.API.clinicaMedica.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.API.clinicaMedica.Model.ProntuarioModel;
@Repository
public interface ProntuarioRepository extends JpaRepository<ProntuarioModel, String>{
    
}
