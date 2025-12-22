package com.hospital.backend.mapper;

import com.hospital.backend.dto.DepartamentoRequestDTO;
import com.hospital.backend.dto.DepartamentoResponseDTO;
import com.hospital.backend.entity.Departamento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartamentoMapper {

    DepartamentoResponseDTO toResponseDTO(Departamento departamento);

    Departamento toEntity(DepartamentoRequestDTO dto);
}
