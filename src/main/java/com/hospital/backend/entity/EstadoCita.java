package com.hospital.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Estado_Cita")
@Getter
@Setter
public class EstadoCita {

    @Id
    @Column(name = "codigo_estado", length = 10)
    private String codigoEstado;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;
}
