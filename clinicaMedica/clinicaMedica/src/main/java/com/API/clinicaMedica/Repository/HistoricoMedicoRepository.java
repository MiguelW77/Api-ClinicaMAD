package com.API.clinicaMedica.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.API.clinicaMedica.Model.HistoricoMedicoPacienteModel;

@Repository
public interface HistoricoMedicoRepository extends JpaRepository<HistoricoMedicoPacienteModel, Long> {
}
