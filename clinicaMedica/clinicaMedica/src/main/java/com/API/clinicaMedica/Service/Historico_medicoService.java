package com.API.clinicaMedica.Service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.API.clinicaMedica.Model.Historico_medicoModel;
import com.API.clinicaMedica.Repository.Historico_medicoRepository;

@Service
public class Historico_medicoService {
    @Autowired
    private Historico_medicoRepository repository;

    public Historico_medicoModel Salvar(Historico_medicoModel historico_medico){
        return repository.save(historico_medico);
    }
    public List<Historico_medicoModel> listartodos(){
        return repository.findAll();
    }
    public Historico_medicoModel BuscarPorId(String id){
        Optional<Historico_medicoModel> historico_medico = repository.findById(id);
        if (historico_medico.isPresent()) {
            return historico_medico.get();
        } else {
            return null;
        }
    }
    public Historico_medicoModel Atualizar(String id, Historico_medicoModel historico_medico){
        Historico_medicoModel historico_medicoExistente = repository.findById(id)
         .orElseThrow(() -> new RuntimeException("Historico Medico não encontrada com o ID: " + id));
         historico_medicoExistente.setDataConsulta(historico_medico.getDataConsulta());
         historico_medicoExistente.setDiagnostico(historico_medico.getDiagnostico());
         historico_medicoExistente.setEspecialidade_medico(historico_medico.getEspecialidade_medico());
         historico_medicoExistente.setProcedimento(historico_medico.getProcedimento());
         historico_medicoExistente.setPrescricao(historico_medico.getPrescricao());
            return repository.save(historico_medicoExistente);
        }
        public void Deletar(String id){
            repository.deleteById(id);
        }
}
