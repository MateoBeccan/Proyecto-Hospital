package com.hospital.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "medico_especialidad")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicoEspecialidad {

    @EmbeddedId
    private MedicoEspecialidadId id;

    @Column(name = "activa")
    private Boolean activa;

    // Relaciones (opcional)
    @MapsId("matriculaMedico")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matricula_medico")
    private Medico medico;

    @MapsId("codigoEspecialidad")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo_especialidad")
    private Especialidad especialidad;
}
