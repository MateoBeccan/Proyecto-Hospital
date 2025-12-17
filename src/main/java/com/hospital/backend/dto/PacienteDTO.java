package com.hospital.backend.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.hospital.backend.entity.Paciente;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@JsonPropertyOrder({ "dni", "nombre", "apellido", "fechaNacimiento", "genero", "estadoCivil", "nacionalidad", "domicilio", "email" })

public class PacienteDTO {

    @NotBlank(message = "El DNI es obligatorio")
    @Size(min = 8, max = 8, message = "El DNI debe tener 8 caracteres")
    private String dni;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser anterior a hoy")
    private LocalDate fechaNacimiento;

    @NotNull(message = "El género es obligatorio")
    private Paciente.Genero genero;

    @NotNull(message = "El estado civil es obligatorio")
    private Paciente.EstadoCivil estadoCivil;

    @NotBlank(message = "La nacionalidad es obligatoria")
    private String nacionalidad;

    private String domicilio;

    @Email(message = "El email debe ser válido")
    private String email;

    // Getters y setters
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    public Paciente.Genero getGenero() { return genero; }
    public void setGenero(Paciente.Genero genero) { this.genero = genero; }
    public Paciente.EstadoCivil getEstadoCivil() { return estadoCivil; }
    public void setEstadoCivil(Paciente.EstadoCivil estadoCivil) { this.estadoCivil = estadoCivil; }
    public String getNacionalidad() { return nacionalidad; }
    public void setNacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; }
    public String getDomicilio() { return domicilio; }
    public void setDomicilio(String domicilio) { this.domicilio = domicilio; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
