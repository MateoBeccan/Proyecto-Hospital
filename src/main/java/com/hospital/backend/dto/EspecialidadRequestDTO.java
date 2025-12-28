package com.hospital.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EspecialidadRequestDTO {

    @NotBlank(message = "El código de especialidad es obligatorio")
    @Size(max = 10)
    private String codigoEspecialidad;

    @NotBlank(message = "El nombre de la especialidad es obligatorio")
    @Size(max = 50)
    private String nombre;

    @Size(max = 255)
    private String descripcion;
}
