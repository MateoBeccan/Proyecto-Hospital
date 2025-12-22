package com.hospital.backend.mapper;

import com.hospital.backend.dto.AfiliacionRequestDTO;
import com.hospital.backend.dto.AfiliacionResponseDTO;
import com.hospital.backend.entity.Afiliacion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AfiliacionMapper {

    // ----------- ENTITY → RESPONSE DTO -----------
    @Mapping(source = "paciente.dni", target = "dni")
    @Mapping(source = "paciente.nombre", target = "nombrePaciente")
    @Mapping(source = "paciente.apellido", target = "apellidoPaciente")
    @Mapping(source = "cobertura.codigoCobertura", target = "codigoCobertura")
    @Mapping(source = "cobertura.nombre", target = "nombreCobertura")
    AfiliacionResponseDTO toResponseDTO(Afiliacion afiliacion);

    // ----------- REQUEST DTO → ENTITY (solo IDs) -----------
    // OJO: esto MapStruct NO puede hacerlo directo porque necesita entidades completas.
    // Así que SOLO se mapean atributos simples.
    @Mapping(target = "paciente", ignore = true)
    @Mapping(target = "cobertura", ignore = true)
    @Mapping(target = "fechaAlta", ignore = true)
    @Mapping(target = "fechaBaja", ignore = true)
    @Mapping(target = "activa", ignore = true)
    Afiliacion toEntity(AfiliacionRequestDTO dto);
}
