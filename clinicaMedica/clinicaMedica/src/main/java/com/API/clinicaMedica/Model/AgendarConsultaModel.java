package com.API.clinicaMedica.Model;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "consulta")
@Getter
@Setter
@NoArgsConstructor
public class AgendarConsultaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate dataConsulta;

    @ManyToOne
    @JsonBackReference(value = "paciente-agendarconsulta")  // Aqui paciente é referência BACK para medico
    @JoinColumn(name = "id_paciente", referencedColumnName = "id")
    private PacienteModel paciente;

    @ManyToOne
    @JsonBackReference(value = "medico-consulta")  // Medico é referência BACK para paciente
    @JoinColumn(name = "id_medico", referencedColumnName = "id")
    private MedicoModel medico;

    @Column(nullable = false)
    private LocalTime horaConsulta;

    @Column(nullable = false, length = 500)
    private String descConsulta;

    @Column(nullable = false, length = 50)
    private String especialidade;

    @Column(nullable = false, length = 50)
    private String statusConsulta;

    @Column(nullable = false, length = 50)
    private String convenio;
}
