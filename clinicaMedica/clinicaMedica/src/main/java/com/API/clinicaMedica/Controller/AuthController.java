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
import com.API.clinicaMedica.Service.MedicoService;
import com.API.clinicaMedica.Service.PacienteService;


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*" )
public class AuthController {

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private MedicoService medicoService;

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
}


