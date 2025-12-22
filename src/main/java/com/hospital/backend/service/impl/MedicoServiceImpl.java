package com.hospital.backend.service.impl;

import com.hospital.backend.dto.MedicoRequestDTO;
import com.hospital.backend.dto.MedicoResponseDTO;
import com.hospital.backend.entity.Departamento;
import com.hospital.backend.entity.Medico;
import com.hospital.backend.mapper.MedicoMapper;
import com.hospital.backend.repository.DepartamentoRepository;
import com.hospital.backend.repository.MedicoRepository;
import com.hospital.backend.service.MedicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicoServiceImpl implements MedicoService {

    private final MedicoRepository medicoRepository;
    private final DepartamentoRepository departamentoRepository;
    private final MedicoMapper mapper;

    @Override
    public MedicoResponseDTO crearMedico(MedicoRequestDTO dto) {
        Medico medico = mapper.toEntity(dto);

        // Convertir Strings a enums
        medico.setGenero(Medico.Genero.valueOf(dto.getGenero()));
        if (dto.getEstadoCivil() != null) {
            medico.setEstadoCivil(Medico.EstadoCivil.valueOf(dto.getEstadoCivil()));
        }

        // Setear el departamento
        Departamento depto = departamentoRepository.findById(dto.getCodigoDepartamento())
                .orElseThrow(() -> new RuntimeException("Departamento no encontrado"));
        medico.setDepartamento(depto);

        // Guardar
        Medico saved = medicoRepository.save(medico);
        return mapper.toResponseDTO(saved);
    }

    @Override
    public List<MedicoResponseDTO> listarMedicos() {
        return medicoRepository.findAll().stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    @Override
    public MedicoResponseDTO obtenerPorId(String matricula) {
        Medico medico = medicoRepository.findById(matricula)
                .orElseThrow(() -> new RuntimeException("Medico no encontrado"));
        return mapper.toResponseDTO(medico);
    }

    @Override
    public MedicoResponseDTO actualizarMedico(String matricula, MedicoRequestDTO dto) {
        Medico medico = medicoRepository.findById(matricula)
                .orElseThrow(() -> new RuntimeException("Medico no encontrado"));

        // Actualizar campos
        medico.setNombre(dto.getNombre());
        medico.setApellido(dto.getApellido());
        medico.setDni(dto.getDni());
        medico.setFechaNacimiento(dto.getFechaNacimiento());
        medico.setNacionalidad(dto.getNacionalidad());
        medico.setDomicilio(dto.getDomicilio());
        medico.setEmail(dto.getEmail());
        medico.setTelefonoPrincipal(dto.getTelefonoPrincipal());

        // Convertir Strings a enums
        medico.setGenero(Medico.Genero.valueOf(dto.getGenero()));
        if (dto.getEstadoCivil() != null) {
            medico.setEstadoCivil(Medico.EstadoCivil.valueOf(dto.getEstadoCivil()));
        }

        // Actualizar departamento
        Departamento depto = departamentoRepository.findById(dto.getCodigoDepartamento())
                .orElseThrow(() -> new RuntimeException("Departamento no encontrado"));
        medico.setDepartamento(depto);

        // Guardar cambios
        Medico updated = medicoRepository.save(medico);
        return mapper.toResponseDTO(updated);
    }

    @Override
    public void eliminarMedico(String matricula) {
        Medico medico = medicoRepository.findById(matricula)
                .orElseThrow(() -> new RuntimeException("Medico no encontrado"));
        medicoRepository.delete(medico);
    }
}
