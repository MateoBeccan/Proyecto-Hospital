package com.hospital.backend.service;

import com.hospital.backend.dto.HistoriaClinicaRequestDTO;
import com.hospital.backend.dto.HistoriaClinicaResponseDTO;
import com.hospital.backend.entity.Cita;
import com.hospital.backend.entity.HistoriaClinica;
import com.hospital.backend.entity.Paciente;
import com.hospital.backend.mapper.HistoriaClinicaMapper;
import com.hospital.backend.repository.CitaRepository;
import com.hospital.backend.repository.HistoriaClinicaRepository;
import com.hospital.backend.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoriaClinicaService {

    private final HistoriaClinicaRepository repository;
    private final PacienteRepository pacienteRepository;
    private final CitaRepository citaRepository;
    private final HistoriaClinicaMapper mapper;

    public HistoriaClinicaResponseDTO crear(HistoriaClinicaRequestDTO dto) {

        Paciente paciente = pacienteRepository.findById(dto.getDniPaciente())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        Cita cita = null;
        if (dto.getCodigoCita() != null) {
            cita = citaRepository.findById(dto.getCodigoCita())
                    .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
        }

        HistoriaClinica hc = mapper.toEntity(dto, paciente, cita);
        repository.save(hc);

        return mapper.toDTO(hc);
    }

    public HistoriaClinicaResponseDTO obtener(String id) {
        HistoriaClinica hc = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));
        return mapper.toDTO(hc);
    }

    public List<HistoriaClinicaResponseDTO> listarPorPaciente(String dni) {
        return repository.findByPaciente_Dni(dni)
                .stream().map(mapper::toDTO).toList();
    }

    public List<HistoriaClinicaResponseDTO> listarPorCita(String codigo) {
        return repository.findByCita_CodigoCita(codigo)
                .stream().map(mapper::toDTO).toList();
    }

    public List<HistoriaClinicaResponseDTO> filtrar(String dni, LocalDate desde, LocalDate hasta, String tipo) {

        return repository.findAll().stream()
                .filter(h -> dni == null || h.getPaciente().getDni().equals(dni))
                .filter(h -> desde == null || !h.getFechaRegistro().isBefore(desde))
                .filter(h -> hasta == null || !h.getFechaRegistro().isAfter(hasta))
                .filter(h -> tipo == null ||
                        (h.getCita() != null && h.getCita().getTipo().name().equalsIgnoreCase(tipo))
                )
                .map(mapper::toDTO)
                .toList();
    }

}
