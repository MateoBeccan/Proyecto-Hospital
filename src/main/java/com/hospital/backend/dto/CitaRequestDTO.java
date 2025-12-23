package com.hospital.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CitaRequestDTO {
    private String codigoCita;
    private String dniPaciente;
    private String matriculaMedico;
    private String codigoEstado;
    private String tipo; // CONSULTA, INTERNACION, ESTUDIO
    private LocalDateTime fechaHora;
    private String observaciones;
    private List<CitaMedicamentoDTO> medicamentos;
}
