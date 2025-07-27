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
import org.springframework.web.bind.annotation.RestController;

import com.API.clinicaMedica.Model.ProntuarioModel;
import com.API.clinicaMedica.Service.ProntuarioService;

import jakarta.persistence.Table;

@RestController
@CrossOrigin
@Table(name = "prontuario")
public class ProntuarioController {
    @Autowired
    private ProntuarioService service;

    @GetMapping
    public List<ProntuarioModel> ListarTodos(){
        return service.ListarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProntuarioModel> BuscarPorId(@PathVariable String id){
        ProntuarioModel prontuario = service.BuscarPorId(id);
        if (prontuario != null) {
            return ResponseEntity.ok(prontuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ProntuarioModel Salvar(ProntuarioModel prontuario){
        return service.Salvar(prontuario);
    }

    @PutMapping
    public ResponseEntity<ProntuarioModel> Atualizar(@PathVariable String id, ProntuarioModel prontuario){
        try {
            ProntuarioModel prontuarioAtualizado = service.Atualizar(id, prontuario);
            return ResponseEntity.ok(prontuarioAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> Deletar(@PathVariable String id){
        try {
            service.Deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
         return ResponseEntity.notFound().build();
        }
    }  
}
