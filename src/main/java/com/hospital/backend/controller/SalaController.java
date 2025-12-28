package com.hospital.backend.controller;

import com.hospital.backend.dto.SalaRequestDTO;
import com.hospital.backend.dto.SalaResponseDTO;
import com.hospital.backend.service.SalaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salas")
@RequiredArgsConstructor
public class SalaController {

    private final SalaService service;

    @PostMapping
    public ResponseEntity<SalaResponseDTO> crear(@RequestBody SalaRequestDTO dto) {
        return ResponseEntity.ok(service.crear(dto));
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<SalaResponseDTO> obtener(@PathVariable String codigo) {
        return ResponseEntity.ok(service.obtener(codigo));
    }

    @GetMapping
    public ResponseEntity<List<SalaResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/departamento/{codigo}")
    public ResponseEntity<List<SalaResponseDTO>> listarPorDepartamento(@PathVariable String codigo) {
        return ResponseEntity.ok(service.listarPorDepartamento(codigo));
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<SalaResponseDTO> actualizar(
            @PathVariable String codigo,
            @RequestBody SalaRequestDTO dto
    ) {
        return ResponseEntity.ok(service.actualizar(codigo, dto));
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> eliminar(@PathVariable String codigo) {
        service.eliminar(codigo);
        return ResponseEntity.noContent().build();
    }
}
