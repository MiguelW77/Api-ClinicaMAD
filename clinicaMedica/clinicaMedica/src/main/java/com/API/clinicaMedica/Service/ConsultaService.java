package com.API.clinicaMedica.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.API.clinicaMedica.Model.AgendarConsultaModel;
import com.API.clinicaMedica.Model.MedicoModel;
import com.API.clinicaMedica.Model.PacienteModel;
import com.API.clinicaMedica.Repository.ConsultaRepository;
import com.API.clinicaMedica.Repository.MedicoRepository;
import com.API.clinicaMedica.Repository.PacienteRepository;

import jakarta.persistence.EntityNotFoundException;



@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository repository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    public AgendarConsultaModel salvar(AgendarConsultaModel consulta) {
        //MedicoModel medico = medicoRepository.findById(medicoId)
    //.orElseThrow(() -> new EntityNotFoundException("Médico não encontrado"));

//PacienteModel paciente = pacienteRepository.findById(id)
    //.orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado"));

//consulta.setMedico(medico);
//consulta.setPaciente(paciente);
        return repository.save(consulta);
    }

    public List<AgendarConsultaModel> listarTodos() {
        return repository.findAll();
    }

    public AgendarConsultaModel buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public AgendarConsultaModel atualizar(Long id, AgendarConsultaModel consulta) {
        AgendarConsultaModel existente = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Consulta não encontrada com ID: " + id));

        existente.setDataConsulta(consulta.getDataConsulta());
        existente.setHoraConsulta(consulta.getHoraConsulta());
        existente.setDescConsulta(consulta.getDescConsulta());
        existente.setEspecialidade(consulta.getEspecialidade());

        return repository.save(existente);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
