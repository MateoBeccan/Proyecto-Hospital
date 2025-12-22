package com.hospital.backend.service;

import com.hospital.backend.dto.CitaRequestDTO;
import com.hospital.backend.dto.CitaResponseDTO;

import java.util.List;

public interface CitaService {

    CitaResponseDTO crearCita(CitaRequestDTO dto);

    List<CitaResponseDTO> listarPorPaciente(String dni);

    List<CitaResponseDTO> listarPorMedico(String matricula);

    CitaResponseDTO obtenerPorId(String id);

    void eliminarCita(String id);
}
