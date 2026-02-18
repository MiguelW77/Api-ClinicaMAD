package com.API.clinicaMedica.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.API.clinicaMedica.Model.MedicoModel;

@Repository
public interface MedicoRepository extends JpaRepository<MedicoModel, Long> {
     Optional<MedicoModel> findByEmailAndSenha(String email, String senha);
      @Query("SELECT m FROM MedicoModel m WHERE m.especialidade = :especialidade AND m.id NOT IN (" +
       "SELECT c.medico.id FROM AgendarConsultaModel c WHERE c.dataConsulta = :dataConsulta)")
List<MedicoModel> findMedicosDisponiveisPorEspecialidadeEData(
    @Param("especialidade") String especialidade,
    @Param("dataConsulta") LocalDate dataConsulta);
}

