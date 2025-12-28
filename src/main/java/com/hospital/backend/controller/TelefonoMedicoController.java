package com.hospital.backend.controller;

import com.hospital.backend.dto.TelefonoMedicoRequestDTO;
import com.hospital.backend.dto.TelefonoMedicoResponseDTO;
import com.hospital.backend.service.TelefonoMedicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/telefono-medico")
@RequiredArgsConstructor
public class TelefonoMedicoController {

    private final TelefonoMedicoService service;

    @PostMapping
    public ResponseEntity<TelefonoMedicoResponseDTO> create(@RequestBody TelefonoMedicoRequestDTO req) {
        return ResponseEntity.ok(
                service.toDTO(service.create(req))
        );
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<List<TelefonoMedicoResponseDTO>> getByMedico(@PathVariable String matricula) {
        return ResponseEntity.ok(service.getByMedico(matricula));
    }

    @PutMapping("/{id}/desactivar")
    public ResponseEntity<String> desactivar(@PathVariable String id) {
        service.desactivar(id);
        return ResponseEntity.ok("Teléfono desactivado correctamente");
    }
}
