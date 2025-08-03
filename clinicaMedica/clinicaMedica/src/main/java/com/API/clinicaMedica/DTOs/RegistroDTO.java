package com.API.clinicaMedica.DTOs;

import com.API.clinicaMedica.Model.UsuarioModel;

public record RegistroDTO(String email, String senha, UsuarioModel role) {

}
