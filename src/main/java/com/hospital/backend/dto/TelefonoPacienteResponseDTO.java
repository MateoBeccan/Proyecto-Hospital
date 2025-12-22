package com.hospital.backend.dto;

import com.hospital.backend.entity.TelefonoPaciente;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TelefonoPacienteResponseDTO {

    private String idTelefono;
    private String dni;
    private String numeroTelefono;
    private TelefonoPaciente.TipoTelefono tipo;
    private boolean esPrincipal;
    private boolean activo;
    private LocalDateTime fechaCreacion;
}
