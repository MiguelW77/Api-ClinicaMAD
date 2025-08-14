package com.API.clinicaMedica.DTO;

import java.time.format.DateTimeFormatter;

import com.API.clinicaMedica.Model.AgendarConsultaModel;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ConsultaDTO {
private Long id;
    private String dataConsulta;
    private String horaConsulta;
    private String especialidade;
    private String nomeMedico;

       public ConsultaDTO(AgendarConsultaModel consulta) {
        this.id = consulta.getId();

        // Formatar LocalDate para string "dd/MM/yyyy"
        DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dataConsulta = consulta.getDataConsulta().format(dataFormatter);

        // Formatar LocalTime para string "HH:mm"
        DateTimeFormatter horaFormatter = DateTimeFormatter.ofPattern("HH:mm");
        this.horaConsulta = consulta.getHoraConsulta().format(horaFormatter);

        this.especialidade = consulta.getEspecialidade();
        this.nomeMedico = consulta.getMedico().getNome();
    }
}
