package com.hospital.backend.controller;

import com.hospital.backend.dto.MedicoEspecialidadRequestDTO;
import com.hospital.backend.service.MedicoEspecialidadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/medico-especialidad")
@RequiredArgsConstructor
public class MedicoEspecialidadController {

    private final MedicoEspecialidadService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody MedicoEspecialidadRequestDTO req) {
        return ResponseEntity.ok(service.create(req));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/medico/{matricula}")
    public ResponseEntity<?> getByMedico(@PathVariable String matricula) {
        return ResponseEntity.ok(service.getByMedico(matricula));
    }

    @GetMapping("/especialidad/{codigo}")
    public ResponseEntity<?> getByEspecialidad(@PathVariable String codigo) {
        return ResponseEntity.ok(service.getByEspecialidad(codigo));
    }

    // Baja lógica usando clave compuesta
    @DeleteMapping("/{matricula}/{codigo}")
    public ResponseEntity<?> desactivar(
            @PathVariable String matricula,
            @PathVariable String codigo) {

        service.desactivar(matricula, codigo);
        return ResponseEntity.ok("Asignación desactivada");
    }

    @PutMapping("/activar/{matricula}/{codigo}")
    public ResponseEntity<?> activar(
            @PathVariable String matricula,
            @PathVariable String codigo) {

        service.activar(matricula, codigo);
        return ResponseEntity.ok("Asignación activada correctamente");
    }


}
