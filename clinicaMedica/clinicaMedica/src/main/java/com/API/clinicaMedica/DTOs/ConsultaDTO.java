package com.API.clinicaMedica.DTOs;

import java.time.LocalDate;
import java.time.LocalTime;

import com.API.clinicaMedica.Model.AgendarConsultaModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ConsultaDTO {
    private LocalDate data;
    private LocalTime hora;
    private String nomePaciente;
    private String nomeMedico;
    private String especialidade;
    private String convenio;
    private String descConsulta;


    public ConsultaDTO(AgendarConsultaModel model){
        this.data = model.getDataConsulta();
        this.hora = model.getHoraConsulta();
        this.nomePaciente = model.getPaciente().getNome();
        this.nomeMedico = model.getMedico().getNome();
        this.especialidade = model.getEspecialidade();
        this.convenio = model.getConvenio();
        this.descConsulta = model.getDescConsulta();
        
    }
    
    

}
