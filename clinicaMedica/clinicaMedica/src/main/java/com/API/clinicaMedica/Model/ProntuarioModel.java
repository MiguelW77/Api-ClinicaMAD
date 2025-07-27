package com.API.clinicaMedica.Model;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "prontuario")
@Getter
@Setter
@NoArgsConstructor
public class ProntuarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(nullable = false, length = 500)
    private String nome_medico;

    @Column(nullable = false, length = 15)
    private String crm_medico;

    @Column(nullable = false, length = 500)
    private String sintomas;

    @Column(nullable = false, length = 500)
    private String diagnostico;

    @Column(nullable = false)
    @Lob
    private String prescricao;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_medico" , referencedColumnName = "id", nullable = false)
    private MedicoModel medico;


}
