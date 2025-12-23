package com.hospital.backend.service.impl;

import com.hospital.backend.dto.MedicamentoRequestDTO;
import com.hospital.backend.dto.MedicamentoResponseDTO;
import com.hospital.backend.entity.Medicamento;
import com.hospital.backend.mapper.MedicamentoMapper;
import com.hospital.backend.repository.MedicamentoRepository;
import com.hospital.backend.service.MedicamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicamentoServiceImpl implements MedicamentoService {

    private final MedicamentoRepository repo;
    private final MedicamentoMapper mapper;

    @Override
    @Transactional
    public MedicamentoResponseDTO crear(MedicamentoRequestDTO dto) {
        if(repo.existsById(dto.getCodigoMedicamento()))
            throw new RuntimeException("Medicamento ya existe");
        Medicamento entity = mapper.toEntity(dto);
        return mapper.toResponseDTO(repo.save(entity));
    }

    @Override
    @Transactional
    public MedicamentoResponseDTO actualizar(String codigo, MedicamentoRequestDTO dto) {
        Medicamento entity = repo.findById(codigo)
                .orElseThrow(() -> new RuntimeException("Medicamento no encontrado"));
        mapper.updateEntityFromDTO(dto, entity);
        return mapper.toResponseDTO(repo.save(entity));
    }

    @Override
    public MedicamentoResponseDTO obtenerPorId(String codigo) {
        return repo.findById(codigo)
                .map(mapper::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Medicamento no encontrado"));
    }

    @Override
    public List<MedicamentoResponseDTO> listarTodos() {
        return repo.findAll().stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void eliminar(String codigo) {
        Medicamento entity = repo.findById(codigo)
                .orElseThrow(() -> new RuntimeException("Medicamento no encontrado"));
        repo.delete(entity);
    }
}
