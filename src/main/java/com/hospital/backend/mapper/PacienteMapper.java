package com.hospital.backend.mapper;

import com.hospital.backend.dto.PacienteRequestDTO;
import com.hospital.backend.dto.PacienteResponseDTO;
import com.hospital.backend.entity.Paciente;
import org.springframework.stereotype.Component;


@Component
public class PacienteMapper {

    public Paciente toEntity(PacienteRequestDTO dto) {
        return Paciente.builder()
                .dni(dto.getDni())
                .nombre(dto.getNombre())
                .apellido(dto.getApellido())
                .fechaNacimiento(dto.getFechaNacimiento())
                .genero(dto.getGenero())
                .estadoCivil(dto.getEstadoCivil())
                .nacionalidad(dto.getNacionalidad())
                .domicilio(dto.getDomicilio())
                .codigoPostal(dto.getCodigoPostal())
                .ciudad(dto.getCiudad())
                .pais(dto.getPais())
                .email(dto.getEmail())
                .build();
    }

    public PacienteResponseDTO toDTO(Paciente paciente) {
        return new PacienteResponseDTO(
                paciente.getDni(),
                paciente.getNombre(),
                paciente.getApellido(),
                paciente.getFechaNacimiento(),
                paciente.getGenero(),
                paciente.getEstadoCivil(),
                paciente.getNacionalidad(),
                paciente.getDomicilio(),
                paciente.getCodigoPostal(),
                paciente.getCiudad(),
                paciente.getPais(),
                paciente.getEmail(),
                paciente.getFechaCreacion()
        );
    }
}

