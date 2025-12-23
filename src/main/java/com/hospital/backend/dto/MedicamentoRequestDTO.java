package com.hospital.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MedicamentoRequestDTO {
    private String codigoMedicamento;
    private String nombre;
    private String descripcion;
    private String proveedor;
    private String lote;
    private LocalDate fechaVencimiento;
    private Integer stock;
}
