package com.hospital.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartamentoRequestDTO {

    private String codigoDepartamento;
    private String nombre;
    private String descripcion;
}
