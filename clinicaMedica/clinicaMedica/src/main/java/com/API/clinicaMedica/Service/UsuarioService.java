package com.API.clinicaMedica.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.API.clinicaMedica.Model.MedicoModel;
import com.API.clinicaMedica.Model.PacienteModel;
import com.API.clinicaMedica.Model.UsuarioModel;
import com.API.clinicaMedica.Repository.UsuarioRepository;

public class UsuarioService {

    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private BCryptPasswordEncoder passwordEncoder;


public void cadastrarPaciente(PacienteModel paciente){
    UsuarioModel user = new  UsuarioModel();
    user.setEmail(paciente.getEmail());
    user.setSenha(passwordEncoder.encode(paciente.getSenha()));
    user.setRole("ROLE_PACIENTE");
    user.setPaciente(paciente);
    usuarioRepository.save(user);
}
public void cadastrarMedico(MedicoModel medico){
    UsuarioModel user = new UsuarioModel();
    user.setCpf(medico.getCpf());
    user.setSenha(passwordEncoder.encode(medico.getSenha()));
    user.setRole("ROLE_MEDICO");
    user.setMedico(medico);
    usuarioRepository.save(user);
    
}
}
