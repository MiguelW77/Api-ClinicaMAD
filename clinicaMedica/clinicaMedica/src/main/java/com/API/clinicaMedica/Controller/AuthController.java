package com.API.clinicaMedica.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.API.clinicaMedica.DTO.LoginRequest;
import com.API.clinicaMedica.Model.MedicoModel;
import com.API.clinicaMedica.Model.PacienteModel;
import com.API.clinicaMedica.Repository.MedicoRepository;
import com.API.clinicaMedica.Repository.PacienteRepository;


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*" )
public class AuthController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // Verifica paciente
        PacienteModel paciente = pacienteRepository.findByEmailAndSenha(
                loginRequest.getEmail(), loginRequest.getSenha());

        if (paciente != null) {
            paciente.setSenha(null); // Remove senha antes de retornar
            return ResponseEntity.ok(paciente);
        }

        // Verifica médico
        MedicoModel medico = medicoRepository.findByEmailAndSenha(
                loginRequest.getEmail(), loginRequest.getSenha());

        if (medico != null) {
            medico.setSenha(null); 
            return ResponseEntity.ok(medico);
        }

       
        return ResponseEntity.status(401).body("Email ou senha inválidos");
    }
}


