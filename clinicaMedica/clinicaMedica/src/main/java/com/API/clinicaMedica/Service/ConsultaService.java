package com.API.clinicaMedica.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.API.clinicaMedica.Model.ConsultaModel;
import com.API.clinicaMedica.Repository.ConsultaRepository;


@Service
public class ConsultaService {

    private ConsultaRepository repository;

     public ConsultaModel Salvar(ConsultaModel consulta) {
        return repository.save(consulta);
    }


    public List<ConsultaModel> ListarTodos() {
        return repository.findAll();
    }

    
    public ConsultaModel BuscarPorId(String id) {
        Optional<ConsultaModel> consulta = repository.findById(id);
        if (consulta.isPresent()) {
            return consulta.get();
        } else {
            return null;
        }
    }
       

         public ConsultaModel Atualizar(String id, ConsultaModel consulta) {
    ConsultaModel consultaExistente = repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Consulta não encontrada com o ID: " + id));

    consultaExistente.setHoraConsulta(consulta.getHoraConsulta());
    consultaExistente.setDataConsulta(consulta.getDataConsulta());

            return repository.save(consultaExistente);
    
  }

    public void Deletar(String id) {
        repository.deleteById(id);
    }
    
}
