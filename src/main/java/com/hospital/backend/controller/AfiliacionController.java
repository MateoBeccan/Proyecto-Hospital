package com.hospital.backend.controller;

import com.hospital.backend.dto.AfiliacionRequestDTO;
import com.hospital.backend.dto.AfiliacionResponseDTO;
import com.hospital.backend.service.AfiliacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/afiliaciones")
@RequiredArgsConstructor
public class AfiliacionController {

    private final AfiliacionService afiliacionService;

    @PostMapping
    public AfiliacionResponseDTO crear(@RequestBody AfiliacionRequestDTO dto) {
        return afiliacionService.crearAfiliacion(dto);
    }

    @GetMapping
    public List<AfiliacionResponseDTO> listar() {
        return afiliacionService.listar();
    }

    @GetMapping("/{id}")
    public AfiliacionResponseDTO obtener(@PathVariable String id) {
        return afiliacionService.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable String id) {
        afiliacionService.eliminar(id);
    }
}
