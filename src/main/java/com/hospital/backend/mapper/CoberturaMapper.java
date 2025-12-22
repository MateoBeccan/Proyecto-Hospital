package com.hospital.backend.mapper;

import com.hospital.backend.dto.CoberturaDTO;
import com.hospital.backend.entity.Cobertura;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CoberturaMapper {

    @Mapping(source = "tipo", target = "tipo", qualifiedByName = "tipoToString")
    CoberturaDTO toDTO(Cobertura entity);

    @Mapping(source = "tipo", target = "tipo", qualifiedByName = "stringToTipo")
    Cobertura toEntity(CoberturaDTO dto);

    @Named("tipoToString")
    default String tipoToString(Cobertura.TipoCobertura tipo) {
        return tipo != null ? tipo.getValue() : null;
    }

    @Named("stringToTipo")
    default Cobertura.TipoCobertura stringToTipo(String value) {
        return value != null ? Cobertura.TipoCobertura.fromValue(value) : null;
    }
}
