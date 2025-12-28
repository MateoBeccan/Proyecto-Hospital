package com.hospital.backend.dto;

import lombok.Data;


@Data
public class MedicoEspecialidadResponseDTO {
    private String matriculaMedico;
    private String nombreMedico;
    private String apellidoMedico;
    private String codigoEspecialidad;
    private String nombreEspecialidad;
    private Boolean activa;
}
