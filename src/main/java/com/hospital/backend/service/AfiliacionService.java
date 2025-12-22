package com.hospital.backend.service;

import com.hospital.backend.dto.AfiliacionRequestDTO;
import com.hospital.backend.dto.AfiliacionResponseDTO;
import java.util.List;

public interface AfiliacionService {

    AfiliacionResponseDTO crearAfiliacion(AfiliacionRequestDTO dto);

    List<AfiliacionResponseDTO> listar();

    AfiliacionResponseDTO obtenerPorId(String id);

    void eliminar(String id);
}
