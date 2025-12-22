package com.hospital.backend.repository;

import com.hospital.backend.entity.Cita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, String> {

    List<Cita> findByPacienteDni(String dni);

    List<Cita> findByMedicoMatricula(String matricula);
}
