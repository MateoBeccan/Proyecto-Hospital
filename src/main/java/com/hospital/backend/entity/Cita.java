package com.hospital.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "cita", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<com.hospital.backend.entity.CitaMedicamento> medicamentos = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.fechaCreacion = LocalDateTime.now();
    }

    public enum TipoCita {
        CONSULTA, INTERNACION, ESTUDIO, REVISION, CIRUGIA, VACUNACION, EMERGENCIA, OTRO
    }
}
