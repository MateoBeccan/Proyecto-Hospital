package com.hospital.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cobertura")
@Getter
@Setter
public class Cobertura {

    @Id
    @Column(name = "codigo_cobertura", length = 10)
    private String codigoCobertura;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "tipo", nullable = false)
    @Convert(converter = TipoCoberturaConverter.class) // usamos converter
    private TipoCobertura tipo;

    public enum TipoCobertura {
        OBRA_SOCIAL("obra social"),
        PREPAGA("prepaga"),
        SIN_COBERTURA("sin cobertura");

        private final String value;

        TipoCobertura(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static TipoCobertura fromValue(String value) {
            for (TipoCobertura t : TipoCobertura.values()) {
                if (t.value.equalsIgnoreCase(value)) {
                    return t;
                }
            }
            throw new IllegalArgumentException("Valor inválido: " + value);
        }
    }
}
