package com.hospital.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class MedicoSalaResponseDTO {

    private String idAsignacion;
    private List<String> especialidad;


    // Médico
    private String matriculaMedico;
    private String nombreMedico;
    private String apellidoMedico;

    // Sala
    private String codigoSala;
    private String nombreSala;
    private String tipoSala;
    private String estadoSala;
    private Integer capacidad;
    private Integer piso;

    // Datos de asignación
    private LocalDate fechaDesde;
    private LocalDate fechaHasta;
    private boolean activa;
}
