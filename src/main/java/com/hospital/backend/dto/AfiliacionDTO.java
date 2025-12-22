package com.hospital.backend.dto;


import lombok.Getter;
import lombok.Setter;
import java.sql.Date;


@Getter
@Setter
public class AfiliacionDTO {
    private String idAfiliacion;
    private String dni;
    private String codigoCobertura;
    private String numeroAfiliado;
    private Date fechaAlta;
    private Date fechaBaja;
    private boolean activa;
}