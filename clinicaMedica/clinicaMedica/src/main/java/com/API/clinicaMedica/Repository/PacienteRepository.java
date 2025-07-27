package com.API.clinicaMedica.Repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.API.clinicaMedica.Model.PacienteModel;

@Repository
public interface PacienteRepository extends JpaRepository<PacienteModel, String> {
    Optional<PacienteRepository> findByEmail(String email);
}

