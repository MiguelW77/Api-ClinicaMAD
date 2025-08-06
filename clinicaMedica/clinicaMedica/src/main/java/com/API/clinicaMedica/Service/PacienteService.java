package com.API.clinicaMedica.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.API.clinicaMedica.Model.PacienteModel;
import com.API.clinicaMedica.Repository.PacienteRepository;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    public PacienteModel salvar(PacienteModel paciente) {
        return repository.save(paciente);
    }

    public List<PacienteModel> listarTodos() {
        return repository.findAll();
    }

    public PacienteModel buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public PacienteModel atualizar(Long id, PacienteModel paciente) {
        PacienteModel existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado com o ID: " + id));

        existente.setNome(paciente.getNome());
        existente.setCpf(paciente.getCpf());
        // Atualize os demais campos aqui conforme necessário

        return repository.save(existente);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
