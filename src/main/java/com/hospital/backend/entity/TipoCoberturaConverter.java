package com.hospital.backend.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoCoberturaConverter implements AttributeConverter<Cobertura.TipoCobertura, String> {

    @Override
    public String convertToDatabaseColumn(Cobertura.TipoCobertura tipoCobertura) {
        return tipoCobertura != null ? tipoCobertura.getValue() : null;
    }

    @Override
    public Cobertura.TipoCobertura convertToEntityAttribute(String dbValue) {
        return dbValue != null ? Cobertura.TipoCobertura.fromValue(dbValue) : null;
    }
}
