package com.API.clinicaMedica.DTOs;

import java.util.List;

import com.API.clinicaMedica.Model.AgendarConsultaModel;
import com.API.clinicaMedica.Model.ExamesModel;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class AgendaDTO {
    private List<ConsultaDTO> consultas;
    private List<ExamesDTO> exames; 

    public AgendaDTO(List<AgendarConsultaModel> consultas , List<ExamesModel> exames){
        this.consultas = consultas.stream()
                                  .map(ConsultaDTO::new )
                               .toList();

        this.exames = exames.stream()
                                .map(ExamesDTO:: new)
                            .toList();
    }

}
