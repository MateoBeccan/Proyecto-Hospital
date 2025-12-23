package com.hospital.backend.controller;

import com.hospital.backend.dto.MedicamentoRequestDTO;
import com.hospital.backend.dto.MedicamentoResponseDTO;
import com.hospital.backend.service.MedicamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicamentos")
@RequiredArgsConstructor
public class MedicamentoController {

    private final MedicamentoService service;

    @PostMapping
    public ResponseEntity<MedicamentoResponseDTO> crear(@RequestBody MedicamentoRequestDTO dto) {
        return ResponseEntity.ok(service.crear(dto));
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<MedicamentoResponseDTO> actualizar(@PathVariable String codigo,
                                                             @RequestBody MedicamentoRequestDTO dto) {
        return ResponseEntity.ok(service.actualizar(codigo, dto));
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<MedicamentoResponseDTO> obtener(@PathVariable String codigo) {
        return ResponseEntity.ok(service.obtenerPorId(codigo));
    }

    @GetMapping
    public ResponseEntity<List<MedicamentoResponseDTO>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> eliminar(@PathVariable String codigo) {
        service.eliminar(codigo);
        return ResponseEntity.noContent().build();
    }
}
