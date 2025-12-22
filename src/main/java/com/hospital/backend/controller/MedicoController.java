package com.hospital.backend.controller;

import com.hospital.backend.dto.MedicoRequestDTO;
import com.hospital.backend.dto.MedicoResponseDTO;
import com.hospital.backend.service.MedicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicos")
@RequiredArgsConstructor
public class MedicoController {

    private final MedicoService service;

    @PostMapping
    public MedicoResponseDTO crear(@RequestBody MedicoRequestDTO dto) {
        return service.crearMedico(dto);
    }

    @GetMapping
    public List<MedicoResponseDTO> listar() {
        return service.listarMedicos();
    }

    @GetMapping("/{matricula}")
    public MedicoResponseDTO obtener(@PathVariable String matricula) {
        return service.obtenerPorId(matricula);
    }

    @PutMapping("/{matricula}")
    public MedicoResponseDTO actualizar(@PathVariable String matricula,
                                        @RequestBody MedicoRequestDTO dto) {
        return service.actualizarMedico(matricula, dto);
    }

    @DeleteMapping("/{matricula}")
    public void eliminar(@PathVariable String matricula) {
        service.eliminarMedico(matricula);
    }
}
