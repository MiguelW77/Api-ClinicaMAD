package com.API.clinicaMedica.Model;

import java.sql.Date;

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
@Getter
@Setter
@NoArgsConstructor
@Table(name = "paciente")
public class PacienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, length = 11, unique = true)
    private String cpf;

    @Column(nullable = true, length = 15)
    private String telefone;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 100)
    private String senha;

    @Column
    private String termos;

    @Column(nullable = false)
    private Date dataNascimento;

    @Column(nullable = false, length = 50)
    private String genero;

    @Column(nullable = true, length = 250)
    private String endereco;

    @Column(nullable = true, length = 15)
    private String cep;

    // Médico responsável, se necessário
    @ManyToOne
    @JoinColumn(name = "medico_id")
    private MedicoModel medico;
}
