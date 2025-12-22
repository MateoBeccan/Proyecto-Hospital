package com.hospital.backend.controller;

import com.hospital.backend.dto.DepartamentoRequestDTO;
import com.hospital.backend.dto.DepartamentoResponseDTO;
import com.hospital.backend.service.DepartamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departamentos")
@RequiredArgsConstructor
public class DepartamentoController {

    private final DepartamentoService service;

    @PostMapping
    public DepartamentoResponseDTO crear(@RequestBody DepartamentoRequestDTO dto) {
        return service.crearDepartamento(dto);
    }

    @GetMapping
    public List<DepartamentoResponseDTO> listar() {
        return service.listarDepartamentos();
    }

    @GetMapping("/{codigo}")
    public DepartamentoResponseDTO obtener(@PathVariable String codigo) {
        return service.obtenerPorId(codigo);
    }

    @DeleteMapping("/{codigo}")
    public void eliminar(@PathVariable String codigo) {
        service.eliminarDepartamento(codigo);
    }
}
