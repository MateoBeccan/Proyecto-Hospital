package com.hospital.backend.service.impl;

import com.hospital.backend.dto.TelefonoPacienteRequestDTO;
import com.hospital.backend.dto.TelefonoPacienteResponseDTO;
import com.hospital.backend.entity.Paciente;
import com.hospital.backend.entity.TelefonoPaciente;
import com.hospital.backend.mapper.TelefonoPacienteMapper;
import com.hospital.backend.repository.PacienteRepository;
import com.hospital.backend.repository.TelefonoPacienteRepository;
import com.hospital.backend.service.TelefonoPacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TelefonoPacienteServiceImpl implements TelefonoPacienteService {

    private final TelefonoPacienteRepository repo;
    private final PacienteRepository pacienteRepo;
    private final TelefonoPacienteMapper mapper;

    @Override
    public TelefonoPacienteResponseDTO crearTelefono(TelefonoPacienteRequestDTO dto) {
        Paciente paciente = pacienteRepo.findById(dto.getDni())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        // Si el teléfono es principal, desactivar otros principales
        if (dto.isEsPrincipal()) {
            List<TelefonoPaciente> telefonos = repo.findByPacienteDniAndActivoTrue(dto.getDni());
            telefonos.forEach(t -> {
                t.setEsPrincipal(false);
                repo.save(t);
            });
        }

        TelefonoPaciente telefono = mapper.toEntity(dto);
        telefono.setPaciente(paciente);
        telefono.setActivo(true);

        return mapper.toResponseDTO(repo.save(telefono));
    }

    @Override
    public List<TelefonoPacienteResponseDTO> listarPorPaciente(String dni) {
        return repo.findByPacienteDniAndActivoTrue(dni).stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    @Override
    public TelefonoPacienteResponseDTO obtenerPorId(String id) {
        return repo.findById(id)
                .map(mapper::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Teléfono no encontrado"));
    }

    @Override
    public void eliminar(String id) {
        TelefonoPaciente telefono = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Teléfono no encontrado"));
        telefono.setActivo(false);
        repo.save(telefono);
    }
}
