package com.hospital.backend.service;

import com.hospital.backend.dto.DepartamentoRequestDTO;
import com.hospital.backend.dto.DepartamentoResponseDTO;

import java.util.List;

public interface DepartamentoService {

    DepartamentoResponseDTO crearDepartamento(DepartamentoRequestDTO dto);

    List<DepartamentoResponseDTO> listarDepartamentos();

    DepartamentoResponseDTO obtenerPorId(String codigo);

    void eliminarDepartamento(String codigo);
}
