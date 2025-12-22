package com.hospital.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "telefono_paciente")
@Getter
@Setter
public class TelefonoPaciente {

    @Id
    @Column(name = "id_telefono", length = 10)
    private String idTelefono;

    @ManyToOne
    @JoinColumn(name = "dni", nullable = false)
    private Paciente paciente;

    @Column(name = "numero_telefono", nullable = false, length = 20)
    private String numeroTelefono;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoTelefono tipo;

    @Column(name = "es_principal", nullable = false)
    private boolean esPrincipal = false;

    @Column(name = "activo", nullable = false)
    private boolean activo = true;

    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;

    @PrePersist
    public void prePersist() {
        this.fechaCreacion = LocalDateTime.now();
    }

    public enum TipoTelefono {
        movil, fijo, laboral, emergencia
    }
}
