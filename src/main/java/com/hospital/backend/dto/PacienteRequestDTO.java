package com.hospital.backend.dto;

import com.hospital.backend.entity.Genero;
import com.hospital.backend.entity.EstadoCivil;
import lombok.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PacienteRequestDTO {

    @Pattern(regexp = "\\d{8}", message = "El DNI debe tener 8 dígitos")
    private String dni;

    @NotBlank
    private String nombre;

    @NotBlank
    private String apellido;

    @NotNull
    private LocalDate fechaNacimiento;

    @NotNull
    private Genero genero;

    @NotNull
    private EstadoCivil estadoCivil;

    @NotBlank
    private String nacionalidad;

    private String domicilio;

    @Size(max = 10)
    private String codigoPostal;

    private String ciudad;
    private String pais;

    @Email
    private String email;
}
