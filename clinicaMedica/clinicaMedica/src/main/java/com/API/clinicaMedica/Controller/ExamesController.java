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

import com.API.clinicaMedica.Model.ExamesModel;
import com.API.clinicaMedica.Service.ExamesService;

import jakarta.persistence.Table;
@RestController
@CrossOrigin
@RequestMapping("/exames")
@Table(name = "exames")
public class ExamesController {
    @Autowired
    private ExamesService service;

    @GetMapping
    public List<ExamesModel> listarTodos(){
        return service.listarTodos();
    }
    @GetMapping("/{id}")
        public ResponseEntity<ExamesModel> BuscarPorId(@PathVariable String id){
            ExamesModel exames = service.buscarPorId(id);
            if (exames != null) {
                return ResponseEntity.ok(exames);
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        @PostMapping
        public ExamesModel salvar(ExamesModel exames){
            return service.Salvar(exames);
        }
        @PutMapping("/{id}")
        public ResponseEntity<ExamesModel> Atualizar(@PathVariable String id, @RequestBody ExamesModel exames){
            try {
                ExamesModel examesAtualizado = service.Atualizar(id, exames);
                return ResponseEntity.ok(examesAtualizado);
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
                return ResponseEntity.noContent().build();
            }
        }
    }

