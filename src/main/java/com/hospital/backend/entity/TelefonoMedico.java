package com.hospital.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "telefono_medico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TelefonoMedico {

    @Id
    @Column(name = "id_telefono", length = 10)
    private String id;

    @ManyToOne
    @JoinColumn(name = "matricula_medico", nullable = false)
    private Medico medico;

    @Column(name = "numero_telefono", nullable = false, length = 20)
    private String numeroTelefono;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoTelefono tipo;

    @Column(name = "es_principal", nullable = false)
    private boolean esPrincipal = false;

    @Column(nullable = false)
    private boolean activo = true;

    public enum TipoTelefono {
        movil, fijo, laboral, emergencia
    }
}
