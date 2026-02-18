package com.API.clinicaMedica.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "prontuario")
@Getter
@Setter
@NoArgsConstructor
public class ProntuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String nomeMedico;

    @Column(nullable = false, length = 15)
    private String crmMedico;

    @Column(nullable = false, length = 500)
    private String sintomas;

    @Column(nullable = false, length = 500)
    private String diagnostico;

    @Lob
    @Column(nullable = false)
    private String prescricao;

    @ManyToOne
    @JsonBackReference(value = "medico-prontuario")
    @JoinColumn(name = "id_medico")
    private MedicoModel medico;
}
