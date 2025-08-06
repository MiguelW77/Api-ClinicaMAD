package com.API.clinicaMedica.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.API.clinicaMedica.Model.ConsultaModel;
import com.API.clinicaMedica.Repository.ConsultaRepository;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository repository;

    public ConsultaModel salvar(ConsultaModel consulta) {
        return repository.save(consulta);
    }

    public List<ConsultaModel> listarTodos() {
        return repository.findAll();
    }

    public ConsultaModel buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public ConsultaModel atualizar(Long id, ConsultaModel consulta) {
        ConsultaModel existente = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Consulta não encontrada com ID: " + id));

        existente.setDataConsulta(consulta.getDataConsulta());
        existente.setHoraConsulta(consulta.getHoraConsulta());
        existente.setConsulta(consulta.getConsulta());
        existente.setEspecialidade(consulta.getEspecialidade());
        existente.setStatusConsulta(consulta.getStatusConsulta());

        return repository.save(existente);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
