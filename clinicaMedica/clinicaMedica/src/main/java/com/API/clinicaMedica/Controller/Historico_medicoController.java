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

import com.API.clinicaMedica.Model.Historico_medicoModel;
import com.API.clinicaMedica.Service.Historico_medicoService;

import jakarta.persistence.Table;

@RestController
@CrossOrigin
@RequestMapping("/historico-medico")
@Table(name = "historico_medico")
public class Historico_medicoController {
    @Autowired
    private Historico_medicoService service;
    
    @GetMapping
    public List<Historico_medicoModel> listartodos(){
        return service.listartodos();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Historico_medicoModel> buscarPorId(@PathVariable String id){
        Historico_medicoModel historico_medico = service.BuscarPorId(id);
        if (historico_medico != null) {
            return ResponseEntity.ok(historico_medico);
        } else {
            return ResponseEntity.notFound().build();
        }
    }   
    @PostMapping
    public Historico_medicoModel salvar(Historico_medicoModel historico_medico){
        return service.Salvar(historico_medico);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Historico_medicoModel> Atualizar(@PathVariable String id, @RequestBody Historico_medicoModel historico_medico){
        try {
            Historico_medicoModel historico_medicoAtualizado = service.Atualizar(id, historico_medico);
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
