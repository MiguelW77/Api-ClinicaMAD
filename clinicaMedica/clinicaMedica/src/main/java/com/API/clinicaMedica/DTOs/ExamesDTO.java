package com.API.clinicaMedica.DTOs;

import java.time.LocalDate;

import com.API.clinicaMedica.Model.ExamesModel;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ExamesDTO {
    private String nomePaciente;
    private String nomeMedico;
    private LocalDate dataExame; 
    private String tipoExame;

    public ExamesDTO(ExamesModel model){
        this.nomePaciente = model.getPaciente().getNome();
        this.nomeMedico = model.getMedico().getNome();
        this.dataExame = model.getData_exame();
        this.tipoExame = model.getTipo_exame();
    }


}
