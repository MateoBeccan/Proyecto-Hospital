package com.hospital.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Citas")
@Getter
@Setter
public class Cita {

    @Id
    @Column(name = "codigo_cita", length = 10)
    private String codigoCita;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoCita tipo;

    @ManyToOne
    @JoinColumn(name = "dni_paciente", nullable = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "matricula_medico", nullable = false)
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "codigo_estado", nullable = false)
    private EstadoCita estado;

    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;

    @PrePersist
    public void prePersist() {
        this.fechaCreacion = LocalDateTime.now();
    }

    public enum TipoCita {
        CONSULTA, INTERNACION, ESTUDIO
    }
}
