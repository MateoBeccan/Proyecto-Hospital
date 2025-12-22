package com.hospital.backend.controller;

import com.hospital.backend.dto.CoberturaDTO;
import com.hospital.backend.entity.Cobertura;
import com.hospital.backend.mapper.CoberturaMapper;
import com.hospital.backend.service.CoberturaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coberturas")
public class CoberturaController {

    private final CoberturaService service;
    private final CoberturaMapper mapper;

    public CoberturaController(CoberturaService service, CoberturaMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<CoberturaDTO> findAll() {
        return service.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @GetMapping("/{codigo}")
    public CoberturaDTO findById(@PathVariable String codigo) {
        return mapper.toDTO(service.findById(codigo));
    }

    @PostMapping
    public CoberturaDTO create(@RequestBody CoberturaDTO dto) {
        Cobertura cobertura = mapper.toEntity(dto);
        return mapper.toDTO(service.save(cobertura));
    }

    @PutMapping("/{codigo}")
    public CoberturaDTO update(@PathVariable String codigo, @RequestBody CoberturaDTO dto) {
        Cobertura cobertura = mapper.toEntity(dto);
        return mapper.toDTO(service.update(codigo, cobertura));
    }

    @DeleteMapping("/{codigo}")
    public void delete(@PathVariable String codigo) {
        service.delete(codigo);
    }
}
