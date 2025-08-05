package com.API.clinicaMedica.Model;
import java.sql.Date;

import com.API.clinicaMedica.User.Usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@Entity


@NoArgsConstructor
public class PacienteModel extends Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, length = 11, unique = true)
    private String cpf;

    @Column(nullable = false, length = 15)
    private String telefone;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 100)
    private String senha;

    @Column(nullable = true)
    private String termos;

    @Column(nullable = false)
    private Date dataNascimento;

    @Column(nullable = false, length = 50)
    private String genero;

    @Column(nullable = false, length = 250)
    private String endereco;
    
    @Column(nullable = false, length = 15)
    private String cep;

    


}