package com.hospital.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AfiliacionResponseDTO {

    private String idAfiliacion;
    private String dni;
    private String nombrePaciente;
    private String apellidoPaciente;
    private String codigoCobertura;
    private String nombreCobertura;
    private String numeroAfiliado;
    private boolean activa;
    private java.sql.Date fechaAlta;
}
