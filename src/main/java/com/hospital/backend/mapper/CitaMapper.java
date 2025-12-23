package com.hospital.backend.mapper;

import com.hospital.backend.dto.CitaMedicamentoDTO;
import com.hospital.backend.dto.CitaRequestDTO;
import com.hospital.backend.dto.CitaResponseDTO;
import com.hospital.backend.entity.Cita;
import com.hospital.backend.entity.CitaMedicamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CitaMapper {

    @Mapping(source = "paciente.dni", target = "dniPaciente")
    @Mapping(source = "paciente.nombre", target = "nombrePaciente")
    @Mapping(source = "medico.matricula", target = "matriculaMedico")
    @Mapping(source = "medico.nombre", target = "nombreMedico")
    @Mapping(source = "estado.codigoEstado", target = "codigoEstado")
    @Mapping(source = "estado.nombre", target = "estadoNombre")
    @Mapping(target = "tipo", expression = "java(cita.getTipo().name())")
    @Mapping(target = "medicamentos", expression = "java(mapMedicamentos(cita.getMedicamentos()))")
    CitaResponseDTO toResponseDTO(Cita cita);

    default List<CitaMedicamentoDTO> mapMedicamentos(List<CitaMedicamento> list) {
        if(list == null) return Collections.emptyList();
        return list.stream().map(cm -> {
            CitaMedicamentoDTO dto = new CitaMedicamentoDTO();
            dto.setCodigoMedicamento(cm.getMedicamento().getCodigoMedicamento());
            dto.setNombreMedicamento(cm.getMedicamento().getNombre());
            dto.setDosis(cm.getDosis());
            dto.setFrecuencia(cm.getFrecuencia());
            dto.setDuracionTratamiento(cm.getDuracionTratamiento());
            dto.setObservacion(cm.getObservacion());
            dto.setFechaPrescripcion(cm.getFechaPrescripcion());
            return dto;
        }).toList();
    }

    @Mapping(target = "paciente", ignore = true)
    @Mapping(target = "medico", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "medicamentos", ignore = true)
    Cita toEntity(CitaRequestDTO dto);
}
