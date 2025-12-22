package com.hospital.backend.mapper;

import com.hospital.backend.dto.MedicoRequestDTO;
import com.hospital.backend.dto.MedicoResponseDTO;
import com.hospital.backend.entity.Medico;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MedicoMapper {

    @Mapping(source = "departamento.codigoDepartamento", target = "codigoDepartamento")
    @Mapping(source = "departamento.nombre", target = "nombreDepartamento")
    @Mapping(target = "genero", expression = "java(medico.getGenero().name())")
    @Mapping(target = "estadoCivil", expression = "java(medico.getEstadoCivil() != null ? medico.getEstadoCivil().name() : null)")
    MedicoResponseDTO toResponseDTO(Medico medico);

    @Mapping(target = "departamento", ignore = true)
    Medico toEntity(MedicoRequestDTO dto);
}
