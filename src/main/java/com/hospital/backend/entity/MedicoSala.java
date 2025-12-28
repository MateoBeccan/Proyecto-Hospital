package com.hospital.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "medico_sala")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicoSala {

    @Id
    @Column(name = "id_asignacion", length = 36)
    private String id;

    @ManyToOne
    @JoinColumn(name = "matricula_medico", nullable = false)
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "codigo_sala", nullable = false)
    private Sala sala;

    @Column(name = "fecha_desde", nullable = false)
    private LocalDate fechaDesde;

    @Column(name = "fecha_hasta")
    private LocalDate fechaHasta;

    @Column(nullable = false)
    private boolean activa = true;
}
