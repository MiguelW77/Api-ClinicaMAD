package com.API.clinicaMedica.Model;

import java.time.LocalDate;

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
@Table(name = "exames")
@Getter
@Setter
@NoArgsConstructor
public class ExamesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference(value = "paciente-exame")
    @JoinColumn(name = "id_paciente")
    private PacienteModel paciente;

    @ManyToOne
    @JsonBackReference(value = "medico-exame")
    @JoinColumn(name = "id_medico")
    private MedicoModel medico;

    @Column(nullable = false)
    private LocalDate dataExame;

    @Column(nullable = false, length = 100)
    private String tipoExame;
}

