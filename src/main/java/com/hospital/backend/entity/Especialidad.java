package com.hospital.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Especialidad")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Especialidad {

    @Id
    @Column(name = "codigo_especialidad", length = 10)
    private String codigoEspecialidad;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "descripcion", length = 255)
    private String descripcion;


}
