package com.API.clinicaMedica.Controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.context.SecurityContextHolder;

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
import com.API.clinicaMedica.config.security.JwUtil;




@RestController
@RequestMapping("auth")
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
    @Autowired
    private JwUtil jwUtil;

    
   @PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginRequest dto) {
    String tipo = dto.getTipo();
    if ("medico".equalsIgnoreCase(tipo)) {
        Optional<MedicoModel> opt = medicoService.login(dto.getEmail(), dto.getSenha());
        if (opt.isPresent()) {
            String token = jwUtil.generateToken(opt.get().getId().toString(), "ROLE_MEDICO");
             Map<String, Object> resposta = new HashMap<>();
            resposta.put("token", token);
            resposta.put("idMedico", opt.get().getId());
            return ResponseEntity.ok(resposta);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
    } else if ("paciente".equalsIgnoreCase(tipo)) {
        Optional<PacienteModel> opt = pacienteService.login(dto.getEmail(), dto.getSenha());
        if (opt.isPresent()) {
            String token = jwUtil.generateToken(opt.get().getId().toString(), "ROLE_PACIENTE");
            Map<String, Object> resposta = new HashMap<>();
            resposta.put("token", token);
            resposta.put("idPaciente", opt.get().getId());
            return ResponseEntity.ok(resposta);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
    }
    return ResponseEntity.badRequest().body("Tipo inválido");
}
    
    
    @GetMapping("/me")
  public ResponseEntity<?> me() {
    var auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth == null || !auth.isAuthenticated()) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Nenhum usuário");
    }
    String subject = (String) auth.getPrincipal(); 
    Long id = Long.valueOf(subject);
    String role = auth.getAuthorities().stream().findFirst().map(a -> a.getAuthority()).orElse("ROLE_USER");

    if (role.equals("ROLE_MEDICO")) {
        return medicoRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    } else {
        return pacienteRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
}


