package com.hospital.backend.mapper;

import com.hospital.backend.dto.CitaRequestDTO;
import com.hospital.backend.dto.CitaResponseDTO;
import com.hospital.backend.entity.Cita;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CitaMapper {

    @Mapping(source = "paciente.dni", target = "dniPaciente")
    @Mapping(source = "paciente.nombre", target = "nombrePaciente")
    @Mapping(source = "medico.matricula", target = "matriculaMedico")
    @Mapping(source = "medico.nombre", target = "nombreMedico")
    @Mapping(source = "estado.codigoEstado", target = "codigoEstado")
    @Mapping(source = "estado.nombre", target = "estadoNombre")
    @Mapping(target = "tipo", expression = "java(cita.getTipo().name())")
    CitaResponseDTO toResponseDTO(Cita cita);

    @Mapping(target = "paciente", ignore = true)
    @Mapping(target = "medico", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    Cita toEntity(CitaRequestDTO dto);
}
