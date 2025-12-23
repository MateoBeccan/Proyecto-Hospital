package com.hospital.backend.service;

import com.hospital.backend.dto.MedicamentoRequestDTO;
import com.hospital.backend.dto.MedicamentoResponseDTO;

import java.util.List;

public interface MedicamentoService {
    MedicamentoResponseDTO crear(MedicamentoRequestDTO dto);
    MedicamentoResponseDTO actualizar(String codigo, MedicamentoRequestDTO dto);
    MedicamentoResponseDTO obtenerPorId(String codigo);
    List<MedicamentoResponseDTO> listarTodos();
    void eliminar(String codigo);
}
