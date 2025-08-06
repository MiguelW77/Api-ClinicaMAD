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

import com.API.clinicaMedica.Model.PacienteModel;
import com.API.clinicaMedica.Service.PacienteService;

@RestController
@CrossOrigin
@RequestMapping("/pacientes")
public class PacienteController {

     @Autowired
    private PacienteService service;

    @GetMapping
    public List<PacienteModel> listarTodos() {
        return service.ListarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteModel> buscarPorId(@PathVariable String id) {
        PacienteModel paciente = service.BuscarPorId(id);
        if (paciente != null) {
            return ResponseEntity.ok(paciente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public PacienteModel salvar(@RequestBody PacienteModel paciente) {
        return service.Salvar(paciente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteModel> atualizar(@PathVariable String id, @RequestBody PacienteModel paciente) {
        try{
            PacienteModel pacienteAtualizado = service.Atualizar(id, paciente);
            return ResponseEntity.ok(pacienteAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        try {
            service.Deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
}
