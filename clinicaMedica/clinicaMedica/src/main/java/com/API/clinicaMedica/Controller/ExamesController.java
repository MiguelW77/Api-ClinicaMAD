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

import com.API.clinicaMedica.Model.ExamesModel;
import com.API.clinicaMedica.Service.ExamesService;

@RestController
@RequestMapping("/exames")
public class ExamesController {

    @Autowired
    private ExamesService service;

    @GetMapping
    public List<ExamesModel> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamesModel> buscarPorId(@PathVariable Long id) {
        ExamesModel exames = service.buscarPorId(id);
        return exames != null ? ResponseEntity.ok(exames) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ExamesModel> salvar(@RequestBody ExamesModel exames) {
        return ResponseEntity.ok(service.salvar(exames));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExamesModel> atualizar(@PathVariable Long id, @RequestBody ExamesModel exames) {
        try {
            return ResponseEntity.ok(service.atualizar(id, exames));
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
