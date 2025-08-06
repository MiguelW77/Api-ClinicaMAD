package com.API.clinicaMedica.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.API.clinicaMedica.Model.MedicoModel;
import com.API.clinicaMedica.Repository.MedicoRepository;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository repository;

    public MedicoModel salvar(MedicoModel medico) {
        return repository.save(medico);
    }

    public List<MedicoModel> listarTodos() {
        return repository.findAll();
    }

    public MedicoModel buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public MedicoModel atualizar(Long id, MedicoModel medico) {
        MedicoModel existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado com o ID: " + id));

        existente.setNome(medico.getNome());
        existente.setEspecialidade(medico.getEspecialidade());
        existente.setCrm(medico.getCrm());
        existente.setTelefone(medico.getTelefone());
        existente.setEmail(medico.getEmail());

        return repository.save(existente);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
