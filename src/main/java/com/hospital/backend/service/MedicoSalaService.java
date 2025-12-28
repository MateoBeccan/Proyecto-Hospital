package com.hospital.backend.service;

import com.hospital.backend.dto.MedicoSalaRequestDTO;
import com.hospital.backend.dto.MedicoSalaResponseDTO;
import com.hospital.backend.entity.Medico;
import com.hospital.backend.entity.MedicoSala;
import com.hospital.backend.entity.Sala;
import com.hospital.backend.repository.MedicoEspecialidadRepository;
import com.hospital.backend.repository.MedicoRepository;
import com.hospital.backend.repository.MedicoSalaRepository;
import com.hospital.backend.repository.SalaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicoSalaService {

    private final MedicoSalaRepository repo;
    private final MedicoRepository medicoRepo;
    private final SalaRepository salaRepo;
    private final MedicoEspecialidadRepository medicoEspecialidadRepository;

    // =====================================================
    //                 CREAR ASIGNACIÓN
    // =====================================================
    public MedicoSala create(MedicoSalaRequestDTO req) {

        Medico medico = medicoRepo.findById(req.getMatriculaMedico())
                .orElseThrow(() -> new RuntimeException("Médico no encontrado"));

        Sala sala = salaRepo.findById(req.getCodigoSala())
                .orElseThrow(() -> new RuntimeException("Sala no encontrada"));

        // Validación: evitar duplicados activos
        if (repo.existsByMedicoMatriculaAndSalaCodigoSalaAndActivaTrue(
                req.getMatriculaMedico(), req.getCodigoSala())) {
            throw new RuntimeException("El médico ya está asignado a esta sala actualmente.");
        }

        // ==========================================
        //    ID COMPUESTO: MED-{matricula}-SAL-{codigo}-{fecha}
        // ==========================================
        String idCompuesto =
                "MED-" + req.getMatriculaMedico() +
                        "-SAL-" + req.getCodigoSala() +
                        "-" + req.getFechaDesde().toString().replace("-", "");

        MedicoSala ms = MedicoSala.builder()
                .id(idCompuesto)
                .medico(medico)
                .sala(sala)
                .fechaDesde(req.getFechaDesde())
                .fechaHasta(req.getFechaHasta())
                .activa(true)
                .build();

        return repo.save(ms);
    }

    // =====================================================
    //                 CONSULTAS
    // =====================================================
    public List<MedicoSalaResponseDTO> getAll() {
        return repo.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public List<MedicoSalaResponseDTO> getByMedico(String matricula) {
        return repo.findByMedicoMatricula(matricula)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public List<MedicoSalaResponseDTO> getBySala(String codigoSala) {
        return repo.findBySalaCodigoSala(codigoSala)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    // =====================================================
    //                 DESACTIVAR
    // =====================================================

    public void desactivar(String id) {
        MedicoSala ms = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Asignación no encontrada"));

        ms.setActiva(false);
        ms.setFechaHasta(LocalDate.now()); // <<< FECHA DE BAJA AUTOMÁTICA

        repo.save(ms);
    }


    // =====================================================
    //                 MAPPER DTO
    // =====================================================
    public MedicoSalaResponseDTO toResponseDTO(MedicoSala ms) {
        MedicoSalaResponseDTO dto = new MedicoSalaResponseDTO();

        // Datos de la asignación
        dto.setIdAsignacion(ms.getId());
        dto.setFechaDesde(ms.getFechaDesde());
        dto.setFechaHasta(ms.getFechaHasta());
        dto.setActiva(ms.isActiva());

        // Datos del médico
        dto.setMatriculaMedico(ms.getMedico().getMatricula());
        dto.setNombreMedico(ms.getMedico().getNombre());
        dto.setApellidoMedico(ms.getMedico().getApellido());

        // Especialidades activas
        dto.setEspecialidad(
                medicoEspecialidadRepository.findByMedicoMatricula(ms.getMedico().getMatricula())
                        .stream()
                        .filter(me -> me.getActiva())
                        .map(me -> me.getEspecialidad().getNombre())
                        .toList()
        );

        // Datos de la sala
        dto.setCodigoSala(ms.getSala().getCodigoSala());
        dto.setNombreSala(ms.getSala().getNombre());
        dto.setTipoSala(ms.getSala().getTipo().name());
        dto.setEstadoSala(ms.getSala().getEstado().name());
        dto.setCapacidad(ms.getSala().getCapacidad());
        dto.setPiso(ms.getSala().getPiso());

        return dto;
    }

    public void desactivarPorMedicoYSala(String matricula, String codigoSala) {

        MedicoSala ms = repo.findByMedicoMatriculaAndSalaCodigoSala(matricula, codigoSala)
                .orElseThrow(() -> new RuntimeException("No existe la asignación médico-sala"));

        ms.setActiva(false);
        ms.setFechaHasta(LocalDate.now());
        repo.save(ms);
    }

    public MedicoSalaResponseDTO getByMedicoYSala(String matricula, String codigoSala) {
        MedicoSala ms = repo.findByMedicoMatriculaAndSalaCodigoSala(matricula, codigoSala)
                .orElseThrow(() -> new RuntimeException("Asignación no encontrada"));
        return toResponseDTO(ms);
    }


}
