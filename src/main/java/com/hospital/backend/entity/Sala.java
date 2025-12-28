package com.hospital.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "salas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sala {

    @Id
    @Column(name = "codigo_sala", length = 10)
    private String codigoSala;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(length = 255)
    private String descripcion;

    @Column(name = "codigo_departamento", nullable = false, length = 10)
    private String codigoDepartamento;

    @Column(nullable = false)
    private Integer capacidad;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoSala tipo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoSala estado;

    @Column(nullable = false)
    private Integer piso;

    @Column(name = "numero_habitacion", length = 10)
    private String numeroHabitacion;

    public enum TipoSala {
        consultorio, quirófano, guardia, sala_común, diagnóstico
    }

    public enum EstadoSala {
        operativa, mantenimiento, cerrada
    }
}
