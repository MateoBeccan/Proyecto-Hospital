package com.hospital.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MedicoResponseDTO {

    private String matricula;
    private String dni;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private String genero;
    private String nacionalidad;
    private String estadoCivil;
    private String domicilio;
    private String email;
    private String telefonoPrincipal;
    private String codigoDepartamento;
    private String nombreDepartamento;
}
