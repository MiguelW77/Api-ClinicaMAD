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

import com.API.clinicaMedica.Model.AgendarConsultaModel;

import com.API.clinicaMedica.Service.ConsultaService;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService service;

    @GetMapping
    public List<AgendarConsultaModel> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendarConsultaModel> buscarPorId(@PathVariable Long id) {
        AgendarConsultaModel consulta = service.buscarPorId(id);
        return consulta != null ? ResponseEntity.ok(consulta) : ResponseEntity.notFound().build();
    }

    @PostMapping("/consultas")
    public ResponseEntity<AgendarConsultaModel> salvar(@RequestBody AgendarConsultaModel consulta) {
        return ResponseEntity.ok(service.salvar(consulta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendarConsultaModel> atualizar(@PathVariable Long id, @RequestBody AgendarConsultaModel consulta) {
        try {
            return ResponseEntity.ok(service.atualizar(id, consulta));
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
