package com.hospital.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "Cita_Medicamento")
@Getter
@Setter
public class CitaMedicamento {

    @EmbeddedId
    private CitaMedicamentoId id;

    @ManyToOne
    @MapsId("codigoCita")
    @JoinColumn(name = "codigo_cita")
    private Cita cita;

    @ManyToOne
    @MapsId("codigoMedicamento")
    @JoinColumn(name = "codigo_medicamento")
    private Medicamento medicamento;

    @Column(length = 50)
    private String dosis;

    @Column(length = 50)
    private String frecuencia;

    @Column(name = "duracion_tratamiento", length = 50)
    private String duracionTratamiento;

    @Column(columnDefinition = "TEXT")
    private String observacion;

    private LocalDate fechaPrescripcion;
}
