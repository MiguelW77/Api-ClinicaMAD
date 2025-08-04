package com.API.clinicaMedica.User;

public enum UserRole {
    ADMIN("medico"),
    USER("paciente");
    
    private String role;

    UserRole(String role){
        this.role = role;
    }
    public String getRole(){
        return role;
    }
}
