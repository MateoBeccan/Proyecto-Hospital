package com.hospital.backend.dto;

import lombok.Data;

@Data
public class TelefonoMedicoResponseDTO {
    private String idTelefono;
    private String matriculaMedico;
    private String nombreMedico;
    private String apellidoMedico;
    private String numeroTelefono;
    private String tipo;
    private boolean esPrincipal;
    private boolean activo;
}
