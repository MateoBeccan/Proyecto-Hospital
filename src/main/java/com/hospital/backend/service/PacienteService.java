package com.hospital.backend.service;

import com.hospital.backend.dto.PacienteRequestDTO;
import com.hospital.backend.dto.PacienteResponseDTO;
import java.util.List;

public interface PacienteService {

    PacienteResponseDTO crearPaciente(PacienteRequestDTO dto);

    PacienteResponseDTO obtenerPaciente(String dni);

    List<PacienteResponseDTO> listarPacientes();

    PacienteResponseDTO actualizarPaciente(String dni, PacienteRequestDTO dto);

    void eliminarPaciente(String dni);
}
