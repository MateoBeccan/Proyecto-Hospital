package com.hospital.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AfiliacionRequestDTO {

    private String idAfiliacion;
    private String dni;
    private String codigoCobertura;
    private String numeroAfiliado;
}
