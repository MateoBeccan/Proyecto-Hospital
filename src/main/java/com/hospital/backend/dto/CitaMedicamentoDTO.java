package com.hospital.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CitaMedicamentoDTO {
    private String codigoMedicamento;
    private String nombreMedicamento;
    private String dosis;
    private String frecuencia;
    private String duracionTratamiento;
    private String observacion;
    private LocalDate fechaPrescripcion;
}
