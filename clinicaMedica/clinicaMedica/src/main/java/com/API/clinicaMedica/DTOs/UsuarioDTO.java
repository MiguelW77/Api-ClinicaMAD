package com.API.clinicaMedica.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {

    private String email;
    private String cpf;
    private String senha;
    private String role; // MEDICO OU PACIENTE

    public UsuarioDTO(String email, String cpf, String senha, String role) {
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;
        this.role = role;
    }
}
