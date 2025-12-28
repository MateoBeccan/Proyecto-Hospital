package com.hospital.backend.service.impl;

import com.hospital.backend.dto.*;
import com.hospital.backend.entity.Especialidad;
import com.hospital.backend.repository.EspecialidadRepository;
import com.hospital.backend.service.EspecialidadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EspecialidadServiceImpl implements EspecialidadService {

    private final EspecialidadRepository repository;

    @Override
    public EspecialidadResponseDTO crear(EspecialidadRequestDTO dto) {

        if (repository.existsById(dto.getCodigoEspecialidad())) {
            throw new RuntimeException("El código ya existe.");
        }

        if (repository.existsByNombreIgnoreCase(dto.getNombre())) {
            throw new RuntimeException("Ya existe una especialidad con ese nombre.");
        }

        Especialidad esp = Especialidad.builder()
                .codigoEspecialidad(dto.getCodigoEspecialidad())
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .build();

        repository.save(esp);

        return convertToDTO(esp);
    }

    @Override
    public EspecialidadResponseDTO obtenerPorCodigo(String codigo) {
        Especialidad esp = repository.findById(codigo)
                .orElseThrow(() -> new RuntimeException("Especialidad no encontrada"));
        return convertToDTO(esp);
    }

    @Override
    public List<EspecialidadResponseDTO> listar() {
        return repository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public EspecialidadResponseDTO actualizar(String codigo, EspecialidadRequestDTO dto) {

        Especialidad esp = repository.findById(codigo)
                .orElseThrow(() -> new RuntimeException("Especialidad no encontrada"));

        esp.setNombre(dto.getNombre());
        esp.setDescripcion(dto.getDescripcion());

        repository.save(esp);

        return convertToDTO(esp);
    }

    @Override
    public void eliminar(String codigo) {
        if (!repository.existsById(codigo)) {
            throw new RuntimeException("La especialidad no existe");
        }
        repository.deleteById(codigo);
    }

    private EspecialidadResponseDTO convertToDTO(Especialidad esp) {
        return EspecialidadResponseDTO.builder()
                .codigoEspecialidad(esp.getCodigoEspecialidad())
                .nombre(esp.getNombre())
                .descripcion(esp.getDescripcion())
                .build();
    }
}
