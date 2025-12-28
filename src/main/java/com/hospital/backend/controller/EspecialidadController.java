package com.hospital.backend.controller;

import com.hospital.backend.dto.EspecialidadRequestDTO;
import com.hospital.backend.dto.EspecialidadResponseDTO;
import com.hospital.backend.service.EspecialidadService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/especialidades")
@RequiredArgsConstructor
public class EspecialidadController {

    private final EspecialidadService service;

    @PostMapping
    public EspecialidadResponseDTO crear(@Valid @RequestBody EspecialidadRequestDTO dto) {
        return service.crear(dto);
    }

    @GetMapping("/{codigo}")
    public EspecialidadResponseDTO obtener(@PathVariable String codigo) {
        return service.obtenerPorCodigo(codigo);
    }

    @GetMapping
    public List<EspecialidadResponseDTO> listar() {
        return service.listar();
    }

    @PutMapping("/{codigo}")
    public EspecialidadResponseDTO actualizar(
            @PathVariable String codigo,
            @Valid @RequestBody EspecialidadRequestDTO dto
    ) {
        return service.actualizar(codigo, dto);
    }

    @DeleteMapping("/{codigo}")
    public String eliminar(@PathVariable String codigo) {
        service.eliminar(codigo);
        return "Especialidad eliminada correctamente.";
    }
}
