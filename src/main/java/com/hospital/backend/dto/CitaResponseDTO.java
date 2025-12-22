package com.hospital.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CitaResponseDTO {

    private String codigoCita;
    private String dniPaciente;
    private String nombrePaciente;
    private String matriculaMedico;
    private String nombreMedico;
    private String codigoEstado;
    private String estadoNombre;
    private String tipo;
    private LocalDateTime fechaHora;
    private String observaciones;
}
