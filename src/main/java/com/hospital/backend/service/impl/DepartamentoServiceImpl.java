package com.hospital.backend.service.impl;

import com.hospital.backend.dto.DepartamentoRequestDTO;
import com.hospital.backend.dto.DepartamentoResponseDTO;
import com.hospital.backend.entity.Departamento;
import com.hospital.backend.mapper.DepartamentoMapper;
import com.hospital.backend.repository.DepartamentoRepository;
import com.hospital.backend.service.DepartamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartamentoServiceImpl implements DepartamentoService {

    private final DepartamentoRepository repo;
    private final DepartamentoMapper mapper;

    @Override
    public DepartamentoResponseDTO crearDepartamento(DepartamentoRequestDTO dto) {
        Departamento departamento = mapper.toEntity(dto);
        return mapper.toResponseDTO(repo.save(departamento));
    }

    @Override
    public List<DepartamentoResponseDTO> listarDepartamentos() {
        return repo.findAll().stream().map(mapper::toResponseDTO).toList();
    }

    @Override
    public DepartamentoResponseDTO obtenerPorId(String codigo) {
        return repo.findById(codigo)
                .map(mapper::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Departamento no encontrado"));
    }

    @Override
    public void eliminarDepartamento(String codigo) {
        Departamento departamento = repo.findById(codigo)
                .orElseThrow(() -> new RuntimeException("Departamento no encontrado"));
        repo.delete(departamento);
    }
}
