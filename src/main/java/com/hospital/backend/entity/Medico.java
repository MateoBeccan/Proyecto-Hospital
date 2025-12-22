package com.hospital.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Medicos")
@Getter
@Setter
public class Medico {

    @Id
    @Column(length = 20)
    private String matricula;

    @Column(unique = true, nullable = false, length = 8)
    private String dni;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String apellido;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genero genero;

    @Column(nullable = false, length = 50)
    private String nacionalidad;

    @Enumerated(EnumType.STRING)
    private EstadoCivil estadoCivil;

    private String domicilio;

    @Column(unique = true)
    private String email;

    private String telefonoPrincipal;

    @ManyToOne
    @JoinColumn(name = "codigo_departamento", nullable = false)
    private Departamento departamento;

    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;

    @PrePersist
    public void prePersist() {
        this.fechaCreacion = LocalDateTime.now();
    }

    public enum Genero {
        Masculino, Femenino, Otro
    }

    public enum EstadoCivil {
        Soltero, Casado, Divorciado, Viudo, otro, Soltera, Casada, Divorciada, Viuda
    }
}
