package com.hospital.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "Historia_Clinica")
@Getter
@Setter
public class HistoriaClinica {

    @Id
    @Column(name = "id_registro", length = 10)
    private String idRegistro;

    @ManyToOne
    @JoinColumn(name = "dni_paciente", nullable = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "codigo_cita")
    private Cita cita;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDate fechaRegistro;

    @Column(name = "diagnostico_principal")
    private String diagnosticoPrincipal;

    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "procedimientos")
    private String procedimientos;

    @Column(name = "resultados_estudios")
    private String resultadosEstudios;
}
