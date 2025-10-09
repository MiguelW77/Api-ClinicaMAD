package com.API.clinicaMedica.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.API.clinicaMedica.Model.MedicoModel;
import com.API.clinicaMedica.Repository.MedicoRepository;
import com.API.clinicaMedica.Service.MedicoService;

@RestController
@RequestMapping("/medicos")

public class MedicoController {

    @Autowired
    private MedicoService service;
    @Autowired 
    MedicoRepository medicoRepository;

    @GetMapping
    public List<MedicoModel> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/disponiveis")
     public ResponseEntity<List<MedicoModel>> getMedicosDisponiveis(
      @RequestParam String especialidade, 
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {

    List<MedicoModel> medicos = medicoRepository.findMedicosDisponiveisPorEspecialidadeEData(especialidade, data);
    return ResponseEntity.ok(medicos);
  }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoModel> buscarPorId(@PathVariable Long id) {
        MedicoModel medico = service.buscarPorId(id);
        return medico != null ? ResponseEntity.ok(medico) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<MedicoModel> salvar(@RequestBody MedicoModel medico) {
        return ResponseEntity.ok(service.salvar(medico));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoModel> atualizar(@PathVariable Long id, @RequestBody MedicoModel medico) {
        try {
            return ResponseEntity.ok(service.atualizar(id, medico));
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
