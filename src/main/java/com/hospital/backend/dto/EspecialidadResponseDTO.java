package com.hospital.backend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EspecialidadResponseDTO {

    private String codigoEspecialidad;
    private String nombre;
    private String descripcion;
}
