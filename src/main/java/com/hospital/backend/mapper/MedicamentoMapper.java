package com.hospital.backend.mapper;

import com.hospital.backend.dto.MedicamentoRequestDTO;
import com.hospital.backend.dto.MedicamentoResponseDTO;
import com.hospital.backend.entity.Medicamento;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MedicamentoMapper {

    MedicamentoResponseDTO toResponseDTO(Medicamento entity);

    Medicamento toEntity(MedicamentoRequestDTO dto);

    void updateEntityFromDTO(MedicamentoRequestDTO dto, @MappingTarget Medicamento entity);
}
