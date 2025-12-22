package com.hospital.backend.dto;

import com.hospital.backend.entity.Genero;
import com.hospital.backend.entity.EstadoCivil;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PacienteResponseDTO {

    private String dni;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private Genero genero;
    private EstadoCivil estadoCivil;
    private String nacionalidad;
    private String domicilio;
    private String codigoPostal;
    private String ciudad;
    private String pais;
    private String email;
    private LocalDateTime fechaCreacion;
}

