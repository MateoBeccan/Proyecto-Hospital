package com.hospital.backend.service;

import com.hospital.backend.dto.SalaRequestDTO;
import com.hospital.backend.dto.SalaResponseDTO;
import java.util.List;

public interface SalaService {

    SalaResponseDTO crear(SalaRequestDTO dto);

    SalaResponseDTO obtener(String codigo);

    List<SalaResponseDTO> listar();

    List<SalaResponseDTO> listarPorDepartamento(String codigoDepartamento);

    SalaResponseDTO actualizar(String codigo, SalaRequestDTO dto);

    void eliminar(String codigo);
}
