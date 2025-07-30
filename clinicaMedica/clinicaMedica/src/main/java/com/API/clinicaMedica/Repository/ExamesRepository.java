package com.API.clinicaMedica.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.API.clinicaMedica.Model.ExamesModel;
@Repository
public interface ExamesRepository extends JpaRepository<ExamesModel, String>{
    
}
