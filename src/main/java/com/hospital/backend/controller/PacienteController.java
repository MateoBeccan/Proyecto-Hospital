package com.hospital.backend.controller;

import com.hospital.backend.dto.PacienteDTO;
import com.hospital.backend.dto.ResponseDTO;
import com.hospital.backend.entity.Paciente;
import com.hospital.backend.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    private final PacienteService service;

    public PacienteController(PacienteService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<PacienteDTO>>> obtenerTodos() {
        List<PacienteDTO> pacientes = service.obtenerTodos().stream()
                .map(this::mapToDTO)
                .toList();
        return ResponseEntity.ok(new ResponseDTO<>(200, "Lista de pacientes", pacientes));
    }

    @GetMapping("/{dni}")
    public ResponseEntity<ResponseDTO<PacienteDTO>> obtenerPorDni(@PathVariable String dni) {
        return service.obtenerPorDni(dni)
                .map(p -> ResponseEntity.ok(new ResponseDTO<>(200, "Paciente encontrado", mapToDTO(p))))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseDTO<>(404, "Paciente no encontrado", null)));
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<PacienteDTO>> crear(@Valid @RequestBody PacienteDTO dto) {
        Paciente paciente = mapToEntity(dto);
        Paciente guardado = service.guardar(paciente);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO<>(201, "Paciente creado", mapToDTO(guardado)));
    }

    @PutMapping("/{dni}")
    public ResponseEntity<ResponseDTO<PacienteDTO>> actualizar(@PathVariable String dni, @Valid @RequestBody PacienteDTO dto) {
        Paciente paciente = mapToEntity(dto);
        Paciente actualizado = service.actualizar(dni, paciente);
        return ResponseEntity.ok(new ResponseDTO<>(200, "Paciente actualizado", mapToDTO(actualizado)));
    }

    @DeleteMapping("/{dni}")
    public ResponseEntity<ResponseDTO<Void>> eliminar(@PathVariable String dni) {
        service.eliminar(dni);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(new ResponseDTO<>(204, "Paciente eliminado", null));
    }

    // Mapper DTO ↔ Entidad usando Builder
    private Paciente mapToEntity(PacienteDTO dto) {
        return Paciente.builder()
                .dni(dto.getDni())
                .nombre(dto.getNombre())
                .apellido(dto.getApellido())
                .fechaNacimiento(dto.getFechaNacimiento())
                .genero(dto.getGenero())
                .estadoCivil(dto.getEstadoCivil())
                .nacionalidad(dto.getNacionalidad())
                .domicilio(dto.getDomicilio())
                .email(dto.getEmail())
                .build();
    }

    private PacienteDTO mapToDTO(Paciente paciente) {
        PacienteDTO dto = new PacienteDTO();
        dto.setDni(paciente.getDni());
        dto.setNombre(paciente.getNombre());
        dto.setApellido(paciente.getApellido());
        dto.setFechaNacimiento(paciente.getFechaNacimiento());
        dto.setGenero(paciente.getGenero());
        dto.setEstadoCivil(paciente.getEstadoCivil());
        dto.setNacionalidad(paciente.getNacionalidad());
        dto.setDomicilio(paciente.getDomicilio());
        dto.setEmail(paciente.getEmail());
        return dto;
    }
}
