package com.hospital.backend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalaRequestDTO {

    private String codigoSala;
    private String nombre;
    private String descripcion;
    private String codigoDepartamento;
    private Integer capacidad;
    private String tipo;
    private String estado;
    private Integer piso;
    private String numeroHabitacion;
}
