package com.hospital.backend.controller;

import com.hospital.backend.dto.TelefonoPacienteRequestDTO;
import com.hospital.backend.dto.TelefonoPacienteResponseDTO;
import com.hospital.backend.service.TelefonoPacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/telefonos")
@RequiredArgsConstructor
public class TelefonoPacienteController {

    private final TelefonoPacienteService service;

    @PostMapping
    public TelefonoPacienteResponseDTO crear(@RequestBody TelefonoPacienteRequestDTO dto) {
        return service.crearTelefono(dto);
    }

    @GetMapping("/paciente/{dni}")
    public List<TelefonoPacienteResponseDTO> listarPorPaciente(@PathVariable String dni) {
        return service.listarPorPaciente(dni);
    }

    @GetMapping("/{id}")
    public TelefonoPacienteResponseDTO obtener(@PathVariable String id) {
        return service.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable String id) {
        service.eliminar(id);
    }
}
