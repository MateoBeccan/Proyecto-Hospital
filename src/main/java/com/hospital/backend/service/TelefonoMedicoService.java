package com.hospital.backend.service;

import com.hospital.backend.dto.TelefonoMedicoRequestDTO;
import com.hospital.backend.dto.TelefonoMedicoResponseDTO;
import com.hospital.backend.entity.Medico;
import com.hospital.backend.entity.TelefonoMedico;
import com.hospital.backend.repository.MedicoRepository;
import com.hospital.backend.repository.TelefonoMedicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TelefonoMedicoService {

    private final TelefonoMedicoRepository repo;
    private final MedicoRepository medicoRepo;

    // ===============================================
    //              CREAR TELÉFONO
    // ===============================================
    public TelefonoMedico create(TelefonoMedicoRequestDTO req) {

        Medico medico = medicoRepo.findById(req.getMatriculaMedico())
                .orElseThrow(() -> new RuntimeException("Médico no encontrado"));

        // Validar duplicado
        boolean existe = repo.findByMedicoMatricula(req.getMatriculaMedico())
                .stream()
                .anyMatch(t ->
                        t.getNumeroTelefono().equals(req.getNumeroTelefono())
                                && t.isActivo()
                );

        if (existe)
            throw new RuntimeException("El médico ya tiene registrado este número telefónico.");

        // Validar único principal
        if (req.isEsPrincipal()) {
            repo.findByMedicoMatriculaAndEsPrincipalTrue(req.getMatriculaMedico())
                    .ifPresent(t -> {
                        t.setEsPrincipal(false);
                        repo.save(t);
                    });
        }

        // Generar ID
        String id = "TEL-" + req.getMatriculaMedico() + "-"
                + String.format("%03d", repo.findByMedicoMatricula(req.getMatriculaMedico()).size() + 1);

        TelefonoMedico tel = TelefonoMedico.builder()
                .id(id)
                .medico(medico)
                .numeroTelefono(req.getNumeroTelefono())
                .tipo(TelefonoMedico.TipoTelefono.valueOf(req.getTipo()))
                .esPrincipal(req.isEsPrincipal())
                .activo(true)
                .build();

        return repo.save(tel);
    }

    // ===============================================
    //              CONSULTAS
    // ===============================================
    public List<TelefonoMedicoResponseDTO> getByMedico(String matricula) {
        return repo.findByMedicoMatricula(matricula)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // ===============================================
    //                DESACTIVAR
    // ===============================================
    public void desactivar(String id) {
        TelefonoMedico tel = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Teléfono no encontrado"));

        tel.setActivo(false);

        // Si estaba marcado como principal, quitarlo
        if (tel.isEsPrincipal()) {
            tel.setEsPrincipal(false);
        }

        repo.save(tel);
    }

    // ===============================================
    //              MAPPER DTO
    // ===============================================
    public TelefonoMedicoResponseDTO toDTO(TelefonoMedico t) {
        TelefonoMedicoResponseDTO dto = new TelefonoMedicoResponseDTO();
        dto.setIdTelefono(t.getId());
        dto.setNumeroTelefono(t.getNumeroTelefono());
        dto.setTipo(t.getTipo().name());
        dto.setEsPrincipal(t.isEsPrincipal());
        dto.setActivo(t.isActivo());

        // datos del médico
        dto.setMatriculaMedico(t.getMedico().getMatricula());
        dto.setNombreMedico(t.getMedico().getNombre());
        dto.setApellidoMedico(t.getMedico().getApellido());

        return dto;
    }
}
