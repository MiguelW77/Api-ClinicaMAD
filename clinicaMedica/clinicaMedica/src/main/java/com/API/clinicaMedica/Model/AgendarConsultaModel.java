package com.API.clinicaMedica.Model;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataConsulta;  // REMOVE @JoinColumn daqui!

    @ManyToOne
    @JsonBackReference(value = "paciente-agendarconsulta")
    @JoinColumn(name = "id_paciente", referencedColumnName = "id", nullable = true)
    private PacienteModel paciente;

    //@ManyToOne
    //@JsonBackReference(value = "medico-consulta")
    //@JoinColumn(name = "id_medico", referencedColumnName = "id", nullable = true)  // Especifica FK correta
    //private MedicoModel medico;

    @Column(nullable = false)
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime horaConsulta;

    @Column(length = 500)
    private String descConsulta;

    @Column(nullable = false, length = 50)
    private String especialidade;

    @Column(length = 50)
    private String convenio;
    @Column(name = "status_consulta", nullable = false, length = 50)
    private String statusConsulta = "Agendada";
    @Column(name = "consulta", nullable = false, length = 50)
    private String consulta = "Consulta ativa!";
    @ManyToOne
@JoinColumn(name = "medico_id")
@JsonBackReference
private MedicoModel medico;
}

