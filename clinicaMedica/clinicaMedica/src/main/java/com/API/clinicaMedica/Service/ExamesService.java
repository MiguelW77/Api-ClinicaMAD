package com.API.clinicaMedica.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.API.clinicaMedica.Model.ExamesModel;
import com.API.clinicaMedica.Repository.ExamesRepository;

@Service
public class ExamesService {

    @Autowired
    private ExamesRepository repository;

    public ExamesModel salvar(ExamesModel exames) {
        return repository.save(exames);
    }

    public List<ExamesModel> listarTodos() {
        return repository.findAll();
    }

    public ExamesModel buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public ExamesModel atualizar(Long id, ExamesModel exames) {
        ExamesModel existente = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Exame não encontrado com ID: " + id));

        existente.setDataExame(exames.getDataExame());
        existente.setTipoExame(exames.getTipoExame());

        return repository.save(existente);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
