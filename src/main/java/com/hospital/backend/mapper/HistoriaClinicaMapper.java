package com.hospital.backend.mapper;

import com.hospital.backend.dto.HistoriaClinicaRequestDTO;
import com.hospital.backend.dto.HistoriaClinicaResponseDTO;
import com.hospital.backend.entity.HistoriaClinica;
import com.hospital.backend.entity.Paciente;
import com.hospital.backend.entity.Cita;
import org.springframework.stereotype.Component;

@Component
public class HistoriaClinicaMapper {

    public HistoriaClinica toEntity(HistoriaClinicaRequestDTO dto, Paciente paciente, Cita cita) {
        HistoriaClinica hc = new HistoriaClinica();
        hc.setIdRegistro(dto.getIdRegistro());
        hc.setPaciente(paciente);
        hc.setCita(cita);
        hc.setFechaRegistro(dto.getFechaRegistro());
        hc.setDiagnosticoPrincipal(dto.getDiagnosticoPrincipal());
        hc.setObservaciones(dto.getObservaciones());
        hc.setProcedimientos(dto.getProcedimientos());
        hc.setResultadosEstudios(dto.getResultadosEstudios());
        return hc;
    }

    public HistoriaClinicaResponseDTO toDTO(HistoriaClinica hc) {
        HistoriaClinicaResponseDTO dto = new HistoriaClinicaResponseDTO();
        dto.setIdRegistro(hc.getIdRegistro());
        dto.setDniPaciente(hc.getPaciente().getDni());
        dto.setNombrePaciente(hc.getPaciente().getNombre());
        dto.setApellidoPaciente(hc.getPaciente().getApellido());
        dto.setCodigoCita(hc.getCita() != null ? hc.getCita().getCodigoCita() : null);
        dto.setFechaRegistro(hc.getFechaRegistro());
        dto.setDiagnosticoPrincipal(hc.getDiagnosticoPrincipal());
        dto.setObservaciones(hc.getObservaciones());
        dto.setProcedimientos(hc.getProcedimientos());
        dto.setResultadosEstudios(hc.getResultadosEstudios());
        return dto;
    }
}
