package com.API.clinicaMedica.Model;

import java.time.DateTimeException;
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
    private String id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_paciente", referencedColumnName = "id", nullable = false)
    private PacienteModel paciente;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_medico",referencedColumnName = "id", nullable = false)
    private MedicoModel medico;

    @Column(nullable = false)
    private LocalDate data_exame;

    @Column(nullable = false, length = 100)
    private String tipo_exame;

    @Column(nullable = false, length = 255)
    private String local_exame;

   
}
