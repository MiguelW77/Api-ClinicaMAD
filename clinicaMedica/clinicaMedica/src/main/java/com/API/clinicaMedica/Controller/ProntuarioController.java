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

import com.API.clinicaMedica.Model.ProntuarioModel;
import com.API.clinicaMedica.Service.ProntuarioService;

@RestController
@RequestMapping("/prontuarios")
public class ProntuarioController {

    @Autowired
    private ProntuarioService service;

    @GetMapping
    public List<ProntuarioModel> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProntuarioModel> buscarPorId(@PathVariable Long id) {
        ProntuarioModel prontuario = service.buscarPorId(id);
        return prontuario != null ? ResponseEntity.ok(prontuario) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ProntuarioModel> salvar(@RequestBody ProntuarioModel prontuario) {
        return ResponseEntity.ok(service.salvar(prontuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProntuarioModel> atualizar(@PathVariable Long id, @RequestBody ProntuarioModel prontuario) {
        try {
            return ResponseEntity.ok(service.atualizar(id, prontuario));
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
