package com.hospital.backend.service;

import com.hospital.backend.dto.EspecialidadRequestDTO;
import com.hospital.backend.dto.EspecialidadResponseDTO;

import java.util.List;

public interface EspecialidadService {

    EspecialidadResponseDTO crear(EspecialidadRequestDTO dto);

    EspecialidadResponseDTO obtenerPorCodigo(String codigo);

    List<EspecialidadResponseDTO> listar();

    EspecialidadResponseDTO actualizar(String codigo, EspecialidadRequestDTO dto);

    void eliminar(String codigo);
}
