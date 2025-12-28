package com.hospital.backend.service.impl;

import com.hospital.backend.dto.SalaRequestDTO;
import com.hospital.backend.dto.SalaResponseDTO;
import com.hospital.backend.entity.Sala;
import com.hospital.backend.repository.DepartamentoRepository;
import com.hospital.backend.repository.SalaRepository;
import com.hospital.backend.service.SalaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalaServiceImpl implements SalaService {

    private final SalaRepository repository;
    private final DepartamentoRepository departamentoRepository;

    @Override
    public SalaResponseDTO crear(SalaRequestDTO dto) {
        validarDepartamento(dto.getCodigoDepartamento());

        Sala sala = mapearEntidad(dto);
        repository.save(sala);

        return mapearDTO(sala);
    }

    @Override
    public SalaResponseDTO obtener(String codigo) {
        Sala sala = repository.findById(codigo)
                .orElseThrow(() -> new RuntimeException("Sala no encontrada"));

        return mapearDTO(sala);
    }

    @Override
    public List<SalaResponseDTO> listar() {
        return repository.findAll().stream()
                .map(this::mapearDTO)
                .toList();
    }

    @Override
    public List<SalaResponseDTO> listarPorDepartamento(String codigoDepartamento) {
        return repository.findByCodigoDepartamento(codigoDepartamento).stream()
                .map(this::mapearDTO)
                .toList();
    }

    @Override
    public SalaResponseDTO actualizar(String codigo, SalaRequestDTO dto) {
        Sala sala = repository.findById(codigo)
                .orElseThrow(() -> new RuntimeException("Sala no encontrada"));

        validarDepartamento(dto.getCodigoDepartamento());

        sala.setNombre(dto.getNombre());
        sala.setDescripcion(dto.getDescripcion());
        sala.setCodigoDepartamento(dto.getCodigoDepartamento());
        sala.setCapacidad(dto.getCapacidad());
        sala.setTipo(Sala.TipoSala.valueOf(dto.getTipo()));
        sala.setEstado(Sala.EstadoSala.valueOf(dto.getEstado()));
        sala.setPiso(dto.getPiso());
        sala.setNumeroHabitacion(dto.getNumeroHabitacion());

        repository.save(sala);
        return mapearDTO(sala);
    }

    @Override
    public void eliminar(String codigo) {
        if (!repository.existsById(codigo)) {
            throw new RuntimeException("Sala no encontrada");
        }
        repository.deleteById(codigo);
    }

    private void validarDepartamento(String codigoDepto) {
        if (!departamentoRepository.existsById(codigoDepto)) {
            throw new RuntimeException("El departamento no existe");
        }
    }

    private Sala mapearEntidad(SalaRequestDTO dto) {
        return Sala.builder()
                .codigoSala(dto.getCodigoSala())
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .codigoDepartamento(dto.getCodigoDepartamento())
                .capacidad(dto.getCapacidad())
                .tipo(Sala.TipoSala.valueOf(dto.getTipo()))
                .estado(Sala.EstadoSala.valueOf(dto.getEstado()))
                .piso(dto.getPiso())
                .numeroHabitacion(dto.getNumeroHabitacion())
                .build();
    }

    private SalaResponseDTO mapearDTO(Sala sala) {
        String departamentoNombre = departamentoRepository.findById(sala.getCodigoDepartamento())
                .map(dep -> dep.getNombre())
                .orElse("Desconocido");

        return SalaResponseDTO.builder()
                .codigoSala(sala.getCodigoSala())
                .nombre(sala.getNombre())
                .descripcion(sala.getDescripcion())
                .departamentoNombre(departamentoNombre)
                .capacidad(sala.getCapacidad())
                .tipo(sala.getTipo().name())
                .estado(sala.getEstado().name())
                .piso(sala.getPiso())
                .numeroHabitacion(sala.getNumeroHabitacion())
                .build();
    }
}
