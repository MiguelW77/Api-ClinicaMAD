package com.API.clinicaMedica.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.API.clinicaMedica.Model.AgendarConsultaModel;

@Repository
public interface ConsultaRepository extends JpaRepository<AgendarConsultaModel, String> {
    
    // Aqui você pode adicionar métodos personalizados, se necessário
    // Por exemplo, para buscar consultas por paciente ou por data
    // List<ConsultaModel> findByPacienteId(Long pacienteId);
    // List<ConsultaModel> findByDataConsulta(DateTimeException dataConsulta);
    List<AgendarConsultaModel> findByPacienteId(String paciente);
    
    
}