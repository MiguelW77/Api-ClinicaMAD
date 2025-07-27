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

import com.API.clinicaMedica.Model.ConsultaModel;
import com.API.clinicaMedica.Service.ConsultaService;

import jakarta.persistence.Table;

@RestController
@CrossOrigin
@RequestMapping("/consultas")
@Table(name = "consulta")
public class ConsultaController {
    @Autowired
    private ConsultaService service;

    @GetMapping
    public List<ConsultaModel> listarTodos() {
        return service.ListarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaModel> buscarPorId(@PathVariable String id) {
        ConsultaModel consulta = service.BuscarPorId(id);
        if (consulta != null) {
            return ResponseEntity.ok(consulta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ConsultaModel salvar(ConsultaModel consulta) {
        return service.Salvar(consulta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultaModel> atualizar(@PathVariable String id, @RequestBody ConsultaModel consulta) {
        try{
            ConsultaModel consultaAtualizada = service.Atualizar(id, consulta);
            return ResponseEntity.ok(consultaAtualizada);
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
