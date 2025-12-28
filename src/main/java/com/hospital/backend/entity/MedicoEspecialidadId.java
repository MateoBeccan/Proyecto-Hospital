package com.hospital.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class MedicoEspecialidadId implements Serializable {

    @Column(name = "matricula_medico", length = 10)
    private String matriculaMedico;

    @Column(name = "codigo_especialidad", length = 10)
    private String codigoEspecialidad;
}
