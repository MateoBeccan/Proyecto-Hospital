package com.hospital.backend.controller;

import com.hospital.backend.dto.CitaRequestDTO;
import com.hospital.backend.dto.CitaResponseDTO;
import com.hospital.backend.service.CitaService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/citas")
@RequiredArgsConstructor
public class CitaController {

    private final CitaService service;

    @PostMapping
    public ResponseEntity<CitaResponseDTO> crear(@RequestBody CitaRequestDTO dto) {
        return ResponseEntity.ok(service.crearCita(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitaResponseDTO> obtener(@PathVariable String id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    // 🔥 NUEVO: Actualizar cita completa
    @PutMapping("/{id}")
    public ResponseEntity<CitaResponseDTO> actualizar(
            @PathVariable String id,
            @RequestBody CitaRequestDTO dto
    ) {
        return ResponseEntity.ok(service.actualizarCita(id, dto));
    }

    @GetMapping("/paciente/{dni}")
    public ResponseEntity<List<CitaResponseDTO>> listarPorPaciente(@PathVariable String dni) {
        return ResponseEntity.ok(service.listarPorPaciente(dni));
    }

    @GetMapping("/medico/{matricula}")
    public ResponseEntity<List<CitaResponseDTO>> listarPorMedico(@PathVariable String matricula) {
        return ResponseEntity.ok(service.listarPorMedico(matricula));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        service.eliminarCita(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<CitaResponseDTO>> filtrar(
            @RequestParam(required = false) String dniPaciente,
            @RequestParam(required = false) String matriculaMedico,
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) String codigoEstado,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaDesde,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaHasta
    ) {
        return ResponseEntity.ok(
                service.filtrarCitas(dniPaciente, matriculaMedico, tipo, codigoEstado, fechaDesde, fechaHasta)
        );
    }

}
