package com.API.clinicaMedica.DTOs;

import com.API.clinicaMedica.Model.MedicoModel;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class MedicoLoginDTO {
    
    private String cpf;
    private String senha;

    public MedicoLoginDTO(MedicoModel model){
        this.cpf = model.getCpf();
        this.senha = model.getSenha();
    }


}
