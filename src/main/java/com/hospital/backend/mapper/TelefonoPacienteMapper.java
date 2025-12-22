package com.hospital.backend.mapper;

import com.hospital.backend.dto.TelefonoPacienteRequestDTO;
import com.hospital.backend.dto.TelefonoPacienteResponseDTO;
import com.hospital.backend.entity.TelefonoPaciente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TelefonoPacienteMapper {

    @Mapping(source = "paciente.dni", target = "dni")
    TelefonoPacienteResponseDTO toResponseDTO(TelefonoPaciente entity);

    @Mapping(target = "paciente", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    TelefonoPaciente toEntity(TelefonoPacienteRequestDTO dto);
}
