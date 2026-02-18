package com.API.clinicaMedica.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.API.clinicaMedica.Model.ProntuarioModel;
import com.API.clinicaMedica.Repository.ProntuarioRepository;

@Service
public class ProntuarioService {

    @Autowired
    private ProntuarioRepository repository;

    public ProntuarioModel salvar(ProntuarioModel prontuario) {
        return repository.save(prontuario);
    }

    public List<ProntuarioModel> listarTodos() {
        return repository.findAll();
    }

    public ProntuarioModel buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public ProntuarioModel atualizar(Long id, ProntuarioModel prontuario) {
        ProntuarioModel existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prontuário não encontrado com ID: " + id));

        existente.setNomeMedico(prontuario.getNomeMedico());
        existente.setCrmMedico(prontuario.getCrmMedico());
        existente.setSintomas(prontuario.getSintomas());
        existente.setDiagnostico(prontuario.getDiagnostico());
        existente.setPrescricao(prontuario.getPrescricao());

        return repository.save(existente);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
