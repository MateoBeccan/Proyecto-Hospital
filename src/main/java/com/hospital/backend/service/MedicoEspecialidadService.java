package com.hospital.backend.service;

import com.hospital.backend.dto.MedicoEspecialidadRequestDTO;
import com.hospital.backend.dto.MedicoEspecialidadResponseDTO;
import com.hospital.backend.entity.Especialidad;
import com.hospital.backend.entity.Medico;
import com.hospital.backend.entity.MedicoEspecialidad;
import com.hospital.backend.entity.MedicoEspecialidadId;
import com.hospital.backend.repository.EspecialidadRepository;
import com.hospital.backend.repository.MedicoEspecialidadRepository;
import com.hospital.backend.repository.MedicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicoEspecialidadService {

    private final MedicoEspecialidadRepository repo;
    private final MedicoRepository medicoRepo;
    private final EspecialidadRepository espRepo;

    public MedicoEspecialidad create(MedicoEspecialidadRequestDTO req) {

        Medico medico = medicoRepo.findById(req.getMatriculaMedico())
                .orElseThrow(() -> new RuntimeException("Médico no encontrado"));

        Especialidad especialidad = espRepo.findById(req.getCodigoEspecialidad())
                .orElseThrow(() -> new RuntimeException("Especialidad no encontrada"));

        MedicoEspecialidadId id = new MedicoEspecialidadId(
                req.getMatriculaMedico(),
                req.getCodigoEspecialidad()
        );

        // Validar duplicado
        if (repo.existsById(id)) {
            throw new RuntimeException("El médico ya tiene esta especialidad asignada.");
        }

        MedicoEspecialidad me = new MedicoEspecialidad();
        me.setId(id);
        me.setMedico(medico);
        me.setEspecialidad(especialidad);
        me.setActiva(true);

        return repo.save(me);
    }


    public void desactivar(String matricula, String codigo) {

        MedicoEspecialidadId id = new MedicoEspecialidadId(matricula, codigo);

        MedicoEspecialidad me = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe esa asignación"));

        me.setActiva(false);
        repo.save(me);
    }


    public List<MedicoEspecialidadResponseDTO> getAll() {
        return repo.findAll().stream().map(this::toDTO).toList();
    }

    public List<MedicoEspecialidadResponseDTO> getByMedico(String matricula) {
        return repo.findByMedicoMatricula(matricula).stream().map(this::toDTO).toList();
    }

    public List<MedicoEspecialidadResponseDTO> getByEspecialidad(String codigo) {
        return repo.findByEspecialidadCodigoEspecialidad(codigo).stream().map(this::toDTO).toList();
    }

    private MedicoEspecialidadResponseDTO toDTO(MedicoEspecialidad me) {
        MedicoEspecialidadResponseDTO dto = new MedicoEspecialidadResponseDTO();

        dto.setMatriculaMedico(me.getMedico().getMatricula());
        dto.setNombreMedico(me.getMedico().getNombre());
        dto.setApellidoMedico(me.getMedico().getApellido());
        dto.setCodigoEspecialidad(me.getEspecialidad().getCodigoEspecialidad());
        dto.setNombreEspecialidad(me.getEspecialidad().getNombre());
        dto.setActiva(me.getActiva());

        return dto;
    }

    public void activar(String matricula, String codigo) {

        MedicoEspecialidadId id = new MedicoEspecialidadId(matricula, codigo);

        MedicoEspecialidad me = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe esa asignación"));

        me.setActiva(true);
        repo.save(me);
    }

}
