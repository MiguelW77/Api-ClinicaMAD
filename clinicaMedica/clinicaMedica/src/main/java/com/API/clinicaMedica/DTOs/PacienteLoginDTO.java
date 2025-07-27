package com.API.clinicaMedica.DTOs;

import com.API.clinicaMedica.Model.PacienteModel;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class PacienteLoginDTO {
    private String email;
    private String senha;
    
    public PacienteLoginDTO(PacienteModel model){
        this.email = model.getEmail();
        this.senha = model.getSenha();
    }

}
