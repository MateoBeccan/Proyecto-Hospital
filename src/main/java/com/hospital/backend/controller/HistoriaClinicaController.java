package com.hospital.backend.controller;

import com.hospital.backend.dto.HistoriaClinicaRequestDTO;
import com.hospital.backend.dto.HistoriaClinicaResponseDTO;
import com.hospital.backend.service.HistoriaClinicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/historia")
@RequiredArgsConstructor
public class HistoriaClinicaController {

    private final HistoriaClinicaService service;

    @PostMapping
    public ResponseEntity<HistoriaClinicaResponseDTO> crear(@RequestBody HistoriaClinicaRequestDTO dto) {
        return ResponseEntity.ok(service.crear(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoriaClinicaResponseDTO> obtener(@PathVariable String id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @GetMapping("/paciente/{dni}")
    public ResponseEntity<List<HistoriaClinicaResponseDTO>> listarPorPaciente(@PathVariable String dni) {
        return ResponseEntity.ok(service.listarPorPaciente(dni));
    }

    @GetMapping("/cita/{codigo}")
    public ResponseEntity<List<HistoriaClinicaResponseDTO>> listarPorCita(@PathVariable String codigo) {
        return ResponseEntity.ok(service.listarPorCita(codigo));
    }

    @GetMapping("/filtrar")
    public ResponseEntity<List<HistoriaClinicaResponseDTO>> filtrar(
            @RequestParam(required = false) String dniPaciente,
            @RequestParam(required = false) LocalDate fechaDesde,
            @RequestParam(required = false) LocalDate fechaHasta,
            @RequestParam(required = false) String tipoCita
    ) {
        return ResponseEntity.ok(service.filtrar(dniPaciente, fechaDesde, fechaHasta, tipoCita));
    }

}
