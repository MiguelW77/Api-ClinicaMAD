package com.API.clinicaMedica.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.API.clinicaMedica.Model.AgendarConsultaModel;
import com.API.clinicaMedica.Repository.ConsultaRepository;


@Service
public class ConsultaService {

    private ConsultaRepository repository;

     public AgendarConsultaModel Salvar(AgendarConsultaModel consulta) {
        return repository.save(consulta);
    }


    public List<AgendarConsultaModel> ListarTodos() {
        return repository.findAll();
    }

    
    public AgendarConsultaModel BuscarPorId(String id) {
        Optional<AgendarConsultaModel> consulta = repository.findById(id);
        if (consulta.isPresent()) {
            return consulta.get();
        } else {
            return null;
        }
    }
       

         public AgendarConsultaModel Atualizar(String id, AgendarConsultaModel consulta) {
    AgendarConsultaModel consultaExistente = repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Consulta não encontrada com o ID: " + id));

    consultaExistente.setHoraConsulta(consulta.getHoraConsulta());
    consultaExistente.setDataConsulta(consulta.getDataConsulta());

            return repository.save(consultaExistente);
    
  }

    public void Deletar(String id) {
        repository.deleteById(id);
    }
    
}
