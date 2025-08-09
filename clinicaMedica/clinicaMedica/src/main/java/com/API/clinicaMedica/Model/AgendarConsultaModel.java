package com.API.clinicaMedica.Model;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataConsulta;

    @ManyToOne
    @JsonBackReference(value = "paciente-agendarconsulta")  // Aqui paciente é referência BACK para medico
    private PacienteModel paciente;

    @ManyToOne
    @JsonBackReference(value = "medico-consulta")  // Medico é referência BACK para paciente
    private MedicoModel medico;

    @Column(nullable = false)
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime horaConsulta;

    @Column(nullable = false, length = 500)
    private String descConsulta;

    @Column(nullable = false, length = 50)
    private String especialidade;

    @Column(nullable = false, length = 50)
    private String convenio;
}
