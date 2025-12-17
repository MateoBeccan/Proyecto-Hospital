package com.hospital.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Pacientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Paciente {

    @Id
    @Column(length = 8)
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

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_civil", nullable = false)
    private EstadoCivil estadoCivil;

    @Column(nullable = false, length = 50)
    private String nacionalidad;

    @Column()
    private String domicilio;

    @Column(unique = true, length = 100)
    private String email;

    @Column(name = "fecha_creacion", nullable = false)
    @Builder.Default
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    public enum Genero { Masculino, Femenino, Otro }
    public enum EstadoCivil {     Otro,
        Soltero,
        Soltera,
        Casado,
        Casada,
        Divorciado,
        Divorciada,
        Viudo,
        Viuda
    }
}
