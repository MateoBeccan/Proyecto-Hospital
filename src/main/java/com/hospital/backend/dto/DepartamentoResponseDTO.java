package com.hospital.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DepartamentoResponseDTO {

    private String codigoDepartamento;
    private String nombre;
    private String descripcion;
    private LocalDateTime fechaCreacion;
}
