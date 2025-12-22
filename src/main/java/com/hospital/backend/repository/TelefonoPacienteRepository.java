package com.hospital.backend.repository;

import com.hospital.backend.entity.TelefonoPaciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TelefonoPacienteRepository extends JpaRepository<TelefonoPaciente, String> {

    List<TelefonoPaciente> findByPacienteDniAndActivoTrue(String dni);

    List<TelefonoPaciente> findByPacienteDni(String dni);
}
