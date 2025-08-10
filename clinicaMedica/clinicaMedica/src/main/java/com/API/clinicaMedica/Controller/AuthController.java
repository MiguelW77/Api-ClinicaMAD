package com.API.clinicaMedica.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.API.clinicaMedica.DTO.LoginRequest;
import com.API.clinicaMedica.Model.MedicoModel;
import com.API.clinicaMedica.Model.PacienteModel;
import com.API.clinicaMedica.Repository.MedicoRepository;
import com.API.clinicaMedica.Repository.PacienteRepository;
import com.API.clinicaMedica.Service.MedicoService;
import com.API.clinicaMedica.Service.PacienteService;


import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("/auth")
@SessionAttributes({"usuarioLogado", "tipoUsuario"})

public class AuthController {

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private MedicoService medicoService;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest , HttpSession session) {
       String tipo = loginRequest.getTipo();
       if("medico".equalsIgnoreCase(tipo)){
        Optional<MedicoModel> optMedico = medicoService.login(loginRequest.getEmail(), loginRequest.getSenha());
        if(optMedico.isPresent()){
            MedicoModel medico = optMedico.get();
            session.setAttribute("usuarioLogado", medico);
            session.setAttribute("tipoUsuario", "medico");
            return ResponseEntity.ok(medico);   
        } else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body("Email ou senha inválidos para médico");
        }
    }
    else if ("paciente".equalsIgnoreCase(tipo)){
        Optional<PacienteModel> optPaciente = pacienteService.login(loginRequest.getEmail(), loginRequest.getSenha());
        if(optPaciente.isPresent()){
            PacienteModel paciente = optPaciente.get();
            session.setAttribute("usuarioLogado", paciente);
            session.setAttribute("tipoUsuario","paciente");
            return ResponseEntity.ok(paciente);
        }
        }else{
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
        .body("Email ou senha inválidos para paciente");
       }
       {
        return ResponseEntity.badRequest().body("Tipo de usuário inválido");
       }
        
    }
    
    
    @GetMapping("/me")
    public ResponseEntity<?> usuarioLogado(HttpSession session){
        Long usuarioId = (Long) session.getAttribute("usuarioID");
        String tipoUsuario = (String) session.getAttribute("tipoUsuario");
        if(usuarioId == null || tipoUsuario == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Nenhum usuário logado!");
        }
        if("medico".equals(tipoUsuario)){
            return medicoRepository.findById(usuarioId)
            .map(medico -> ResponseEntity.ok().body(medico))
            .orElse(ResponseEntity.notFound().build());
        } else if ("paciente".equals(tipoUsuario)){
            return pacienteRepository.findById(usuarioId)
            .map(paciente -> ResponseEntity.ok().body(paciente))
            .orElse(ResponseEntity.notFound().build());
        }
        return ResponseEntity.badRequest().body("Tipo de usuário inválido!");
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session){
        session.invalidate();
        return ResponseEntity.ok("Logout realizado!");
    }
}


