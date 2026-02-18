package com.API.clinicaMedica.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.API.clinicaMedica.Model.AgendarConsultaModel;

@Repository
public interface ConsultaRepository extends JpaRepository<AgendarConsultaModel, Long> {
    List<AgendarConsultaModel> findByPacienteId(Long pacienteId);
}
