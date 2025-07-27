package com.API.clinicaMedica.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.API.clinicaMedica.Model.MedicoModel;
import com.API.clinicaMedica.Repository.MedicoRepository;

@Service
public class MedicoService {
    @Autowired
    private  MedicoRepository repository;

    public MedicoModel Salvar(MedicoModel medico) {
        return repository.save(medico);
    }


    public List<MedicoModel> ListarTodos() {
        return repository.findAll();
    }

    
    public MedicoModel BuscarPorId(String id) {
       
        Optional<MedicoModel> medico = repository.findById(id);
        if (medico.isPresent()) {
            return medico.get();
        } else {
            return null;
        }
    }

         public MedicoModel Atualizar(String id, MedicoModel medico) {
    MedicoModel medicoExistente = repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Médico não encontrado com o ID: " + id));

    medicoExistente.setNome(medico.getNome());
    medicoExistente.setEspecialidade(medico.getEspecialidade());
    medicoExistente.setCrm(medico.getCrm());

    return repository.save(medicoExistente);

  }

    public void Deletar(String id) {
        repository.deleteById(id);
    }
}