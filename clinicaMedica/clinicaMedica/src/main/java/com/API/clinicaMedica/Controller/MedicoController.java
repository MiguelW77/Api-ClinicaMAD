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

import com.API.clinicaMedica.Model.MedicoModel;
import com.API.clinicaMedica.Service.MedicoService;

import jakarta.persistence.Table;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/medicos")
@Table(name = "medico")
public class MedicoController {

    @Autowired
    private MedicoService service;

    @GetMapping
    public List<MedicoModel> listarTodos() {
        return service.ListarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoModel> buscarPorId(@PathVariable String id) {
        MedicoModel medico = service.BuscarPorId(id);
        if (medico != null) {
            return ResponseEntity.ok(medico);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public MedicoModel salvar(MedicoModel medico) {
        return service.Salvar(medico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoModel> atualizar(@PathVariable String  id, @RequestBody MedicoModel medico) {
        try{
            MedicoModel medicoAtualizado = service.Atualizar(id, medico);
            return ResponseEntity.ok(medicoAtualizado);
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
