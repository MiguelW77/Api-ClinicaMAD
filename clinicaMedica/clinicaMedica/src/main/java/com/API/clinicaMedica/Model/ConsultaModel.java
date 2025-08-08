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
public class ConsultaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate dataConsulta;

    @Column(nullable = false)
    private LocalTime horaConsulta;  // Corrigi de LocalDate para LocalTime

    @Column(nullable = false, length = 500)
    private String consulta;

    @Column(nullable = false, length = 50)
    private String especialidade;

    @Column(nullable = false, length = 50)
    private String statusConsulta;

    @ManyToOne
    @JsonBackReference(value = "paciente-consulta")
    @JoinColumn(name = "id_paciente", referencedColumnName = "id")
    private PacienteModel paciente;

    @ManyToOne
    @JsonBackReference(value = "medico-consulta")
    @JoinColumn(name = "id_medico", referencedColumnName = "id")
    private MedicoModel medico;
}
