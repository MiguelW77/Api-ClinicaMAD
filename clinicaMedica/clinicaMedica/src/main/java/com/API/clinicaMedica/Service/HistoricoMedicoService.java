package com.API.clinicaMedica.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.API.clinicaMedica.Model.HistoricoMedicoPacienteModel;
import com.API.clinicaMedica.Repository.HistoricoMedicoRepository;

@Service
public class HistoricoMedicoService {

    @Autowired
    private HistoricoMedicoRepository repository;

    public HistoricoMedicoPacienteModel salvar(HistoricoMedicoPacienteModel historico) {
        return repository.save(historico);
    }

    public List<HistoricoMedicoPacienteModel> listarTodos() {
        return repository.findAll();
    }

    public HistoricoMedicoPacienteModel buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public HistoricoMedicoPacienteModel atualizar(Long id, HistoricoMedicoPacienteModel historico) {
        HistoricoMedicoPacienteModel existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Histórico Médico não encontrado com ID: " + id));

        existente.setDataConsulta(historico.getDataConsulta());
        existente.setDiagnostico(historico.getDiagnostico());
        existente.setEspecialidadeMedico(historico.getEspecialidadeMedico());
        existente.setProcedimento(historico.getProcedimento());
        existente.setPrescricao(historico.getPrescricao());

        return repository.save(existente);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
