package com.hospital.backend.service.impl;

import com.hospital.backend.dto.PacienteRequestDTO;
import com.hospital.backend.dto.PacienteResponseDTO;
import com.hospital.backend.entity.Paciente;
import com.hospital.backend.mapper.PacienteMapper;
import com.hospital.backend.repository.PacienteRepository;
import com.hospital.backend.service.PacienteService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepo;
    private final PacienteMapper mapper;

    @Override
    public PacienteResponseDTO crearPaciente(PacienteRequestDTO dto) {

        if (pacienteRepo.existsById(dto.getDni()))
            throw new RuntimeException("El paciente ya existe.");

        if (pacienteRepo.existsByEmail(dto.getEmail()))
            throw new RuntimeException("El email ya está registrado.");

        Paciente paciente = mapper.toEntity(dto);
        Paciente guardado = pacienteRepo.save(paciente);
        return mapper.toDTO(guardado);
    }

    @Override
    public PacienteResponseDTO obtenerPaciente(String dni) {
        Paciente paciente = pacienteRepo.findById(dni)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado."));
        return mapper.toDTO(paciente);
    }

    @Override
    public List<PacienteResponseDTO> listarPacientes() {
        return pacienteRepo.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public PacienteResponseDTO actualizarPaciente(String dni, PacienteRequestDTO dto) {
        Paciente paciente = pacienteRepo.findById(dni)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado."));

        paciente.setNombre(dto.getNombre());
        paciente.setApellido(dto.getApellido());
        paciente.setFechaNacimiento(dto.getFechaNacimiento());
        paciente.setGenero(dto.getGenero());
        paciente.setEstadoCivil(dto.getEstadoCivil());
        paciente.setNacionalidad(dto.getNacionalidad());
        paciente.setDomicilio(dto.getDomicilio());
        paciente.setEmail(dto.getEmail());
        paciente.setCodigoPostal(dto.getCodigoPostal());
        paciente.setCiudad(dto.getCiudad());
        paciente.setPais(dto.getPais());


        return mapper.toDTO(pacienteRepo.save(paciente));
    }

    @Override
    public void eliminarPaciente(String dni) {
        if (!pacienteRepo.existsById(dni))
            throw new RuntimeException("Paciente no encontrado.");
        pacienteRepo.deleteById(dni);
    }
}
