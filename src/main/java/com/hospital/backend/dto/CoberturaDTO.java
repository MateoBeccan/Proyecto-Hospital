package com.hospital.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoberturaDTO {
    private String codigoCobertura;
    private String nombre;
    private String tipo; // String que contendrá "obra social", "prepaga" o "sin cobertura"
}
