package com.API.clinicaMedica.Model;
import java.util.List;

import com.API.clinicaMedica.User.Usuario;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import  jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter



@NoArgsConstructor
public class MedicoModel extends Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, length = 11 , unique = true)
    private String cpf;

    @Column(nullable = false, length = 50)
    private String especialidade;

    @Column(length = 15, nullable = false)
    private String crm;

    @Column(length = 15, nullable = false)
    private String telefone;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String senha;

    @Column(nullable = false)
    private String termos;

    @OneToMany(mappedBy = "medico")
    @JsonManagedReference
    private List<AgendarConsultaModel> consultas;
    
    @OneToMany(mappedBy= "medico")
    @JsonManagedReference
    private List<ExamesModel> exames;
    
    @OneToMany(mappedBy = "medico")
    @JsonManagedReference
    private List<ProntuarioModel> prontuarios;

    @OneToMany(mappedBy = "medico")
    @JsonManagedReference
    private List<PacienteModel> pacientes;
}