package com.API.clinicaMedica.Model;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "paciente")
public class PacienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, length = 14, unique = true)
    private String cpf;

    @Column(length = 20)
    private String telefone;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 100)
    private String senha;

    @Column
    private String termos;

    @Column(nullable = false)
    private Date dataNascimento;

    @Column(nullable = false, length = 50)
    private String genero;

    @Column(length = 250)
    private String endereco;

    @Column(length = 15)
    private String cep;

    @ManyToOne
    @JsonBackReference(value = "medico-paciente")  
    @JoinColumn(name = "id_medico")
    private MedicoModel medico;

    @OneToMany(mappedBy = "paciente")
    @JsonManagedReference(value = "paciente-agendarconsulta")  
    private List<AgendarConsultaModel> consulta;

    @OneToMany(mappedBy = "paciente")
    @JsonManagedReference(value = "paciente-agendarconsulta")
    private List<AgendarConsultaModel> agendconsult;

    @OneToMany(mappedBy = "paciente")
    @JsonManagedReference(value = "paciente-exame")
    private List<ExamesModel> exame;

    @OneToMany(mappedBy = "paciente")
    @JsonManagedReference(value = "paciente-historico")
    private List<HistoricoMedicoPacienteModel> histpaciente;
}

