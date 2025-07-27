package com.API.clinicaMedica.Model;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import  jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter

@Table(name = "medico")

@NoArgsConstructor
public class MedicoModel implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String role = "MEDICO";

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
    private List<ConsultaModel> consultas;
    
    @OneToMany(mappedBy= "medico")
    @JsonManagedReference
    private List<ExamesModel> exames;
    
    @OneToMany(mappedBy = "medico")
    @JsonManagedReference
    private List<ProntuarioModel> prontuarios;

    @OneToMany(mappedBy = "medico")
    @JsonManagedReference
    private List<PacienteModel> pacientes;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       return List.of(new SimpleGrantedAuthority("ROLE" + role));
       
    }

    @Override
    public String getPassword() {
       return this.senha;
    }

    @Override
    public String getUsername() {
        return this.cpf;
    }
}