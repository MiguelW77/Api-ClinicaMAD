package com.API.clinicaMedica.DTOs;

import com.API.clinicaMedica.User.UserRole;


public record RegistroDTO(String email, String senha, UserRole role) {

}
