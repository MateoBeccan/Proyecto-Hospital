package com.hospital.backend.service;

import com.hospital.backend.dto.TelefonoPacienteRequestDTO;
import com.hospital.backend.dto.TelefonoPacienteResponseDTO;

import java.util.List;

public interface TelefonoPacienteService {

    TelefonoPacienteResponseDTO crearTelefono(TelefonoPacienteRequestDTO dto);

    List<TelefonoPacienteResponseDTO> listarPorPaciente(String dni);

    TelefonoPacienteResponseDTO obtenerPorId(String id);

    void eliminar(String id);
}
