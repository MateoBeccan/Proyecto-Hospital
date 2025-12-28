package com.hospital.backend.controller;

import com.hospital.backend.dto.MedicoSalaRequestDTO;
import com.hospital.backend.dto.MedicoSalaResponseDTO;
import com.hospital.backend.entity.MedicoSala;
import com.hospital.backend.service.MedicoSalaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medico-sala")
@RequiredArgsConstructor
public class MedicoSalaController {

    private final MedicoSalaService service;

    @PostMapping
    public ResponseEntity<MedicoSalaResponseDTO> create(@RequestBody MedicoSalaRequestDTO req) {
        MedicoSala asignacion = service.create(req);
        return ResponseEntity.ok(service.toResponseDTO(asignacion));
    }

    @GetMapping
    public ResponseEntity<List<MedicoSalaResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/medico/{matricula}")
    public ResponseEntity<List<MedicoSalaResponseDTO>> getByMedico(@PathVariable String matricula) {
        return ResponseEntity.ok(service.getByMedico(matricula));
    }

    @GetMapping("/sala/{codigoSala}")
    public ResponseEntity<List<MedicoSalaResponseDTO>> getBySala(@PathVariable String codigoSala) {
        return ResponseEntity.ok(service.getBySala(codigoSala));
    }

    @PutMapping("/{id}/desactivar")
    public ResponseEntity<String> desactivar(@PathVariable String id) {
        service.desactivar(id);
        return ResponseEntity.ok("Asignación desactivada correctamente");
    }

    @PutMapping("/desactivar/{matricula}/{codigoSala}")
    public ResponseEntity<String> desactivarPorMedicoYSala(
            @PathVariable String matricula,
            @PathVariable String codigoSala) {

        service.desactivarPorMedicoYSala(matricula, codigoSala);
        return ResponseEntity.ok("Asignación desactivada correctamente");
    }

    @GetMapping("/{matricula}/{codigoSala}")
    public ResponseEntity<MedicoSalaResponseDTO> getByMedicoSala(
            @PathVariable String matricula,
            @PathVariable String codigoSala) {

        return ResponseEntity.ok(service.getByMedicoYSala(matricula, codigoSala));
    }


}
