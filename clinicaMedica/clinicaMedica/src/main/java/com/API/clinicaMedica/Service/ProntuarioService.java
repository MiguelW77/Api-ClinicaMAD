package com.API.clinicaMedica.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.API.clinicaMedica.Model.ProntuarioModel;
import com.API.clinicaMedica.Repository.ProntuarioRepository;

@Service
public class ProntuarioService {
    @Autowired
    private ProntuarioRepository repository;

    public ProntuarioModel Salvar(ProntuarioModel prontuario){
        return repository.save(prontuario);
    }

    public List<ProntuarioModel> ListarTodos(){
        return repository.findAll();
    }

    public ProntuarioModel BuscarPorId(String id){
        Optional<ProntuarioModel> prontuario = repository.findById(id);
        if (prontuario.isPresent()) {
            return prontuario.get();
        } else {
            return null;
        }
    }
    public ProntuarioModel Atualizar(String id, ProntuarioModel prontuario){
        ProntuarioModel prontuarioExistente = repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Prontuario não encontrado com o ID:" + id));
        prontuarioExistente.setCrm_medico(prontuario.getCrm_medico());
        prontuarioExistente.setDiagnostico(prontuario.getDiagnostico());
        prontuarioExistente.setNome_medico(prontuario.getNome_medico());
        prontuarioExistente.setPrescricao(prontuario.getPrescricao());
        prontuarioExistente.setSintomas(prontuario.getSintomas());
        return repository.save(prontuarioExistente);
    }
    public void Deletar(String id){
         repository.deleteById(id);
    }
}