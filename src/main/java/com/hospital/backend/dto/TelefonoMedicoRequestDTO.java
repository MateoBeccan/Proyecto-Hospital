package com.hospital.backend.dto;

import lombok.Data;

@Data
public class TelefonoMedicoRequestDTO {
    private String matriculaMedico;
    private String numeroTelefono;
    private String tipo;   // "movil", "fijo", "laboral", "emergencia"
    private boolean esPrincipal;
}
