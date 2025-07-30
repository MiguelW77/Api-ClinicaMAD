package com.API.clinicaMedica.Service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.API.clinicaMedica.Model.HistoricoMedicoPacienteModel;
import com.API.clinicaMedica.Repository.Historico_medicoRepository;

@Service
public class Historico_medicoService {
    @Autowired
    private Historico_medicoRepository repository;

    public HistoricoMedicoPacienteModel Salvar(HistoricoMedicoPacienteModel historico_medico){
        return repository.save(historico_medico);
    }
    public List<HistoricoMedicoPacienteModel> listartodos(){
        return repository.findAll();
    }
    public HistoricoMedicoPacienteModel BuscarPorId(String id){
        Optional<HistoricoMedicoPacienteModel> historico_medico = repository.findById(id);
        if (historico_medico.isPresent()) {
            return historico_medico.get();
        } else {
            return null;
        }
    }
    public HistoricoMedicoPacienteModel Atualizar(String id, HistoricoMedicoPacienteModel historico_medico){
        HistoricoMedicoPacienteModel historico_medicoExistente = repository.findById(id)
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
