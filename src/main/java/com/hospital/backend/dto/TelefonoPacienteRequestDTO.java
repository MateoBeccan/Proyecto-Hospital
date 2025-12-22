package com.hospital.backend.dto;

import com.hospital.backend.entity.TelefonoPaciente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TelefonoPacienteRequestDTO {

    private String idTelefono;

    @NotBlank
    @Pattern(regexp = "\\d{8}", message = "El DNI debe tener 8 dígitos")
    private String dni;

    @NotBlank
    @Pattern(regexp = "\\+?\\d{7,15}", message = "Número de teléfono inválido")
    private String numeroTelefono;

    @NotNull
    private TelefonoPaciente.TipoTelefono tipo;

    private boolean esPrincipal = false;
}
