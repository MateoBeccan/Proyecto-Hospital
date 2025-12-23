package com.hospital.backend.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class CitaMedicamentoId implements Serializable {

    private String codigoCita;
    private String codigoMedicamento;

    public CitaMedicamentoId() {}

    public CitaMedicamentoId(String codigoCita, String codigoMedicamento) {
        this.codigoCita = codigoCita;
        this.codigoMedicamento = codigoMedicamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CitaMedicamentoId)) return false;
        CitaMedicamentoId that = (CitaMedicamentoId) o;
        return Objects.equals(codigoCita, that.codigoCita) &&
                Objects.equals(codigoMedicamento, that.codigoMedicamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoCita, codigoMedicamento);
    }
}
