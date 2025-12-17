package com.hospital.backend.service;

import com.hospital.backend.entity.Paciente;
import com.hospital.backend.exception.BadRequestException;
import com.hospital.backend.exception.ResourceNotFoundException;
import com.hospital.backend.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    private final PacienteRepository repository;

    public PacienteService(PacienteRepository repository) {
        this.repository = repository;
    }

    // Obtener todos los pacientes
    public List<Paciente> obtenerTodos() {
        return repository.findAll();
    }

    // Obtener paciente por DNI
    public Optional<Paciente> obtenerPorDni(String dni) {
        return repository.findById(dni);
    }

    // Guardar nuevo paciente
    public Paciente guardar(Paciente paciente) {
        // Validar DNI duplicado
        if (repository.existsById(paciente.getDni())) {
            throw new BadRequestException("El DNI ya está registrado");
        }

        // Validar email duplicado (si se proporciona email)
        if (paciente.getEmail() != null && repository.findAll().stream()
                .anyMatch(p -> p.getEmail() != null && p.getEmail().equalsIgnoreCase(paciente.getEmail()))) {
            throw new BadRequestException("El email ya está registrado");
        }

        return repository.save(paciente);
    }

    // Actualizar paciente existente
    public Paciente actualizar(String dni, Paciente pacienteActualizado) {
        return repository.findById(dni)
                .map(paciente -> {
                    // Validar si el email a actualizar ya existe en otro paciente
                    if (pacienteActualizado.getEmail() != null && repository.findAll().stream()
                            .anyMatch(p -> !p.getDni().equals(dni) &&
                                    p.getEmail() != null &&
                                    p.getEmail().equalsIgnoreCase(pacienteActualizado.getEmail()))) {
                        throw new BadRequestException("El email ya está registrado por otro paciente");
                    }

                    paciente.setNombre(pacienteActualizado.getNombre());
                    paciente.setApellido(pacienteActualizado.getApellido());
                    paciente.setFechaNacimiento(pacienteActualizado.getFechaNacimiento());
                    paciente.setGenero(pacienteActualizado.getGenero());
                    paciente.setEstadoCivil(pacienteActualizado.getEstadoCivil());
                    paciente.setNacionalidad(pacienteActualizado.getNacionalidad());
                    paciente.setDomicilio(pacienteActualizado.getDomicilio());
                    paciente.setEmail(pacienteActualizado.getEmail());

                    return repository.save(paciente);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Paciente con DNI " + dni + " no encontrado"));
    }

    // Eliminar paciente
    public void eliminar(String dni) {
        if (!repository.existsById(dni)) {
            throw new ResourceNotFoundException("Paciente con DNI " + dni + " no encontrado");
        }
        repository.deleteById(dni);
    }
}
