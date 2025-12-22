package com.hospital.backend.entity;

public enum TipoCobertura {
    OBRA_SOCIAL("obra social"),
    PREPAGA("prepaga"),
    SIN_COBERTURA("sin cobertura");

    private final String value;

    TipoCobertura(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
