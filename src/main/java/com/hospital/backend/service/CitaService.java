package com.hospital.backend.service;

import com.hospital.backend.dto.CitaRequestDTO;
import com.hospital.backend.dto.CitaResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface CitaService {

    // Crear una nueva cita
    CitaResponseDTO crearCita(CitaRequestDTO dto);

    CitaResponseDTO actualizarCita(String id, CitaRequestDTO dto);

    // Listar todas las citas de un paciente
    List<CitaResponseDTO> listarPorPaciente(String dni);

    // Listar todas las citas de un médico
    List<CitaResponseDTO> listarPorMedico(String matricula);

    // Obtener cita por su código
    CitaResponseDTO obtenerPorId(String id);

    // Eliminar cita por su código
    void eliminarCita(String id);


    // Listar citas filtrando por médico, paciente, tipo, estado o rango de fechas
    List<CitaResponseDTO> filtrarCitas(String dniPaciente,
                                       String matriculaMedico,
                                       String tipo,
                                       String codigoEstado,
                                       LocalDateTime fechaDesde,
                                       LocalDateTime fechaHasta);


}

