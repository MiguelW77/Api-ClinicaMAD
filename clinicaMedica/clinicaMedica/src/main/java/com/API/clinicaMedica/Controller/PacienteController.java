package com.API.clinicaMedica.Controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.SecurityContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.API.clinicaMedica.Model.PacienteModel;
import com.API.clinicaMedica.Repository.PacienteRepository;
import com.API.clinicaMedica.Service.PacienteService;



@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    PacienteService service;
    @Autowired
    PacienteRepository pacienteRepository;

    @GetMapping
    public List<PacienteModel> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteModel> buscarPorId(@PathVariable Long id) {
        PacienteModel paciente = service.buscarPorId(id);
        return paciente != null ? ResponseEntity.ok(paciente) : ResponseEntity.notFound().build();
    }

    @PostMapping()
    public PacienteModel salvar(@RequestBody PacienteModel paciente) {
        return service.salvar(paciente);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody PacienteModel pacienteAtualizado){
        var auth = SecurityContextHolder.getContext().getAuthentication();
        String subject = (String) auth.getPrincipal();
        Long idToken = Long.valueOf(subject);
            if(!idToken.equals(id)){
                System.out.println("tentativa de editar outro usuário!");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("voce nao pode editar outro paciente");
            }

        
        Optional<PacienteModel> pacienteLogadoOpt = pacienteRepository.findById(idToken);
        System.out.println("usuario buscado pelo email : " + pacienteLogadoOpt);

        if(pacienteLogadoOpt.isEmpty()){
            System.out.println("usuario nao encontrado");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não encontrado");
        }
        PacienteModel pacienteLogado = pacienteLogadoOpt.get();
        pacienteLogado.setNome(pacienteAtualizado.getNome());
        pacienteLogado.setEmail(pacienteAtualizado.getEmail());
        pacienteLogado.setTelefone(pacienteAtualizado.getTelefone());
        pacienteRepository.save(pacienteLogado);
        System.out.println("dados atualizados com sucesso");
        return ResponseEntity.ok(pacienteLogado);
       
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            service.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
