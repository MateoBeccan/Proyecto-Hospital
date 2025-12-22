package com.hospital.backend.service.impl;

import com.hospital.backend.dto.CitaRequestDTO;
import com.hospital.backend.dto.CitaResponseDTO;
import com.hospital.backend.entity.Cita;
import com.hospital.backend.entity.EstadoCita;
import com.hospital.backend.entity.Medico;
import com.hospital.backend.entity.Paciente;
import com.hospital.backend.mapper.CitaMapper;
import com.hospital.backend.repository.CitaRepository;
import com.hospital.backend.repository.EstadoCitaRepository;
import com.hospital.backend.repository.MedicoRepository;
import com.hospital.backend.repository.PacienteRepository;
import com.hospital.backend.service.CitaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CitaServiceImpl implements CitaService {

    private final CitaRepository repo;
    private final PacienteRepository pacienteRepo;
    private final MedicoRepository medicoRepo;
    private final EstadoCitaRepository estadoRepo;
    private final CitaMapper mapper;

    @Override
    public CitaResponseDTO crearCita(CitaRequestDTO dto) {
        Paciente paciente = pacienteRepo.findById(dto.getDniPaciente())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        Medico medico = medicoRepo.findById(dto.getMatriculaMedico())
                .orElseThrow(() -> new RuntimeException("Médico no encontrado"));
        EstadoCita estado = estadoRepo.findById(dto.getCodigoEstado())
                .orElseThrow(() -> new RuntimeException("Estado de cita no encontrado"));

        Cita cita = mapper.toEntity(dto);
        cita.setPaciente(paciente);
        cita.setMedico(medico);
        cita.setEstado(estado);
        cita.setTipo(Cita.TipoCita.valueOf(dto.getTipo().toUpperCase()));

        return mapper.toResponseDTO(repo.save(cita));
    }

    @Override
    public List<CitaResponseDTO> listarPorPaciente(String dni) {
        return repo.findByPacienteDni(dni).stream().map(mapper::toResponseDTO).toList();
    }

    @Override
    public List<CitaResponseDTO> listarPorMedico(String matricula) {
        return repo.findByMedicoMatricula(matricula).stream().map(mapper::toResponseDTO).toList();
    }

    @Override
    public CitaResponseDTO obtenerPorId(String id) {
        return repo.findById(id).map(mapper::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
    }

    @Override
    public void eliminarCita(String id) {
        Cita cita = repo.findById(id).orElseThrow(() -> new RuntimeException("Cita no encontrada"));
        repo.delete(cita);
    }
}
