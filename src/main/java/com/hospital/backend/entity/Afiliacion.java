package com.hospital.backend.entity;

import com.hospital.backend.entity.Cobertura;
import com.hospital.backend.entity.Paciente;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "Afiliacion")
@Getter
@Setter
public class Afiliacion {

    @Id
    @Column(name = "id_afiliacion", length = 10)
    private String idAfiliacion;

    @ManyToOne
    @JoinColumn(name = "dni", nullable = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "codigo_cobertura", nullable = false)
    private Cobertura cobertura;

    @Column(name = "numero_afiliado", nullable = false, length = 50)
    private String numeroAfiliado;

    @Column(name = "fecha_alta", nullable = false)
    private Date fechaAlta;

    @Column(name = "fecha_baja")
    private Date fechaBaja;

    @Column(name = "activa", nullable = false)
    private boolean activa = true;
}
