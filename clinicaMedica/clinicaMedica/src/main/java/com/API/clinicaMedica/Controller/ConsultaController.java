package com.API.clinicaMedica.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.API.clinicaMedica.Model.AgendarConsultaModel;
import com.API.clinicaMedica.Model.MedicoModel;
import com.API.clinicaMedica.Model.PacienteModel;
import com.API.clinicaMedica.Repository.ConsultaRepository;
import com.API.clinicaMedica.Repository.MedicoRepository;
import com.API.clinicaMedica.Repository.PacienteRepository;
import com.API.clinicaMedica.Service.ConsultaService;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService service;
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;
   

    @GetMapping
    public List<AgendarConsultaModel> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendarConsultaModel> buscarPorId(@PathVariable Long id) {
        AgendarConsultaModel consulta = service.buscarPorId(id);
        return consulta != null ? ResponseEntity.ok(consulta) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<AgendarConsultaModel> criarConsulta(@RequestBody AgendarConsultaModel consulta, Authentication authenticated){
        String emailUsuarioLogado = authenticated.getName();
        PacienteModel paciente = pacienteRepository.findByEmail(emailUsuarioLogado)
        .orElseThrow(()-> new RuntimeException("Paciente não encontrado!"));
        Long medicoId = consulta.getMedico().getId();

        MedicoModel medico = medicoRepository.findById(medicoId)
        .orElseThrow(()-> new RuntimeException("Médico não encontrado!"));
       
       
        consulta.setMedico(medico);
        consulta.setPaciente(paciente);

        AgendarConsultaModel consultaSalva = consultaRepository.save(consulta);
        return ResponseEntity.status(HttpStatus.CREATED).body(consultaSalva);
}

    @PutMapping("/{id}")
    public ResponseEntity<AgendarConsultaModel> atualizar(@PathVariable Long id, @RequestBody AgendarConsultaModel consulta) {
        try {
            return ResponseEntity.ok(service.atualizar(id, consulta));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            service.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
