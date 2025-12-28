package com.hospital.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MedicoSalaRequestDTO {
    private String matriculaMedico;
    private String codigoSala;
    private LocalDate fechaDesde;
    private LocalDate fechaHasta;
}
