package com.API.clinicaMedica.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.API.clinicaMedica.Model.HistoricoMedicoPacienteModel;
import com.API.clinicaMedica.Service.Historico_medicoService;

@RestController
@CrossOrigin
@RequestMapping("/historico-medico")
public class Historico_medicoController {
    @Autowired
    private Historico_medicoService service;
    
    @GetMapping
    public List<HistoricoMedicoPacienteModel> listartodos(){
        return service.listartodos();
    }
    @GetMapping("/{id}")
    public ResponseEntity<HistoricoMedicoPacienteModel> buscarPorId(@PathVariable String id){
        HistoricoMedicoPacienteModel historico_medico = service.BuscarPorId(id);
        if (historico_medico != null) {
            return ResponseEntity.ok(historico_medico);
        } else {
            return ResponseEntity.notFound().build();
        }
    }   
    @PostMapping
    public HistoricoMedicoPacienteModel salvar(HistoricoMedicoPacienteModel historico_medico){
        return service.Salvar(historico_medico);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<HistoricoMedicoPacienteModel> Atualizar(@PathVariable String id, @RequestBody HistoricoMedicoPacienteModel historico_medico){
        try {
            HistoricoMedicoPacienteModel historico_medicoAtualizado = service.Atualizar(id, historico_medico);
            return ResponseEntity.ok(historico_medicoAtualizado);
        } catch (RuntimeException e) {
           return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id){
        try {
            service.Deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
           return ResponseEntity.notFound().build();
        }
    }
}
