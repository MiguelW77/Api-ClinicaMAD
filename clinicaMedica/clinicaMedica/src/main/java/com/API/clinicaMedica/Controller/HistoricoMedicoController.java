package com.API.clinicaMedica.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.API.clinicaMedica.Model.HistoricoMedicoPacienteModel;
import com.API.clinicaMedica.Service.HistoricoMedicoService;

@RestController
@RequestMapping("/historico-medico")
public class HistoricoMedicoController {

    @Autowired
    private HistoricoMedicoService service;

    @GetMapping
    public List<HistoricoMedicoPacienteModel> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoricoMedicoPacienteModel> buscarPorId(@PathVariable Long id) {
        HistoricoMedicoPacienteModel historico = service.buscarPorId(id);
        return historico != null ? ResponseEntity.ok(historico) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<HistoricoMedicoPacienteModel> salvar(@RequestBody HistoricoMedicoPacienteModel historico) {
        return ResponseEntity.ok(service.salvar(historico));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistoricoMedicoPacienteModel> atualizar(@PathVariable Long id, @RequestBody HistoricoMedicoPacienteModel historico) {
        try {
            return ResponseEntity.ok(service.atualizar(id, historico));
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
