package com.hospital.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class HistoriaClinicaResponseDTO {

    private String idRegistro;
    private String dniPaciente;
    private String nombrePaciente;
    private String apellidoPaciente;

    private String codigoCita;

    private LocalDate fechaRegistro;
    private String diagnosticoPrincipal;
    private String observaciones;
    private String procedimientos;
    private String resultadosEstudios;


}
