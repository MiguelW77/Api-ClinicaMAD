package com.API.clinicaMedica.Model;
import java.sql.Date;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.Table;
import lombok.Setter;

@Getter
@Setter

@Entity

@Table(name = "paciente")
@NoArgsConstructor
public class PacienteModel implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String role = "PACIENTE";

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
        
       
    }
    


}