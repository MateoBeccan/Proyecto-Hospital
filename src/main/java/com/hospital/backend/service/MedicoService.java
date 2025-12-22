package com.hospital.backend.service;

import com.hospital.backend.dto.MedicoRequestDTO;
import com.hospital.backend.dto.MedicoResponseDTO;

import java.util.List;

public interface MedicoService {

    MedicoResponseDTO crearMedico(MedicoRequestDTO dto);

    List<MedicoResponseDTO> listarMedicos();

    MedicoResponseDTO obtenerPorId(String matricula);

    MedicoResponseDTO actualizarMedico(String matricula, MedicoRequestDTO dto);


    void eliminarMedico(String matricula);
}
