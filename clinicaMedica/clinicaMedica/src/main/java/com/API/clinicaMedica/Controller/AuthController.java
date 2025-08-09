package com.API.clinicaMedica.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties.Http;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.API.clinicaMedica.DTO.LoginRequest;
import com.API.clinicaMedica.Repository.MedicoRepository;
import com.API.clinicaMedica.Repository.PacienteRepository;
import com.API.clinicaMedica.Service.MedicoService;
import com.API.clinicaMedica.Service.PacienteService;

import io.micrometer.core.ipc.http.HttpSender.Response;
import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*" )
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
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
       String tipo = loginRequest.getTipo();
       if("medico".equalsIgnoreCase(tipo)){
        return medicoService.login(loginRequest);
       }else if ("paciente".equalsIgnoreCase(tipo)){
        return pacienteService.login(loginRequest);

       }else{
        return ResponseEntity.badRequest().body("Tipo inválido");
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


