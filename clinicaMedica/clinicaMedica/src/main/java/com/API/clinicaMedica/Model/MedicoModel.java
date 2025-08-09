package com.API.clinicaMedica.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "medico")
public class MedicoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, length = 15, unique = true)
    private String cpf;

    @Column(nullable = false, length = 50)
    private String especialidade;

    @Column(nullable = false, length = 20)
    private String crm;

    @Column(nullable = false, length = 20)
    private String telefone;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String senha;

    @Column(nullable = false)
    private String termos;

    @OneToMany(mappedBy = "medico")
    @JsonManagedReference(value = "medico-consulta")  // Médico tem consultas
    private List<AgendarConsultaModel> consultas;

    @OneToMany(mappedBy = "medico")
    @JsonManagedReference(value = "medico-exame")
    private List<ExamesModel> exames;

    @OneToMany(mappedBy = "medico")
    @JsonManagedReference(value = "medico-prontuario")
    private List<ProntuarioModel> prontuarios;

    @OneToMany(mappedBy = "medico")
    @JsonManagedReference(value = "medico-paciente")  // Médico tem pacientes
    private List<PacienteModel> pacientes;
}