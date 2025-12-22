package com.hospital.backend.controller;

import com.hospital.backend.dto.PacienteRequestDTO;
import com.hospital.backend.dto.PacienteResponseDTO;
import com.hospital.backend.service.PacienteService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService pacienteService;

    @PostMapping
    public PacienteResponseDTO crear(@RequestBody PacienteRequestDTO dto) {
        return pacienteService.crearPaciente(dto);
    }

    @GetMapping("/{dni}")
    public PacienteResponseDTO obtener(@PathVariable String dni) {
        return pacienteService.obtenerPaciente(dni);
    }

    @GetMapping
    public List<PacienteResponseDTO> listar() {
        return pacienteService.listarPacientes();
    }

    @PutMapping("/{dni}")
    public PacienteResponseDTO actualizar(@PathVariable String dni, @RequestBody PacienteRequestDTO dto) {
        return pacienteService.actualizarPaciente(dni, dto);
    }

    @DeleteMapping("/{dni}")
    public void eliminar(@PathVariable String dni) {
        pacienteService.eliminarPaciente(dni);
    }
}
