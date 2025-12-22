package com.hospital.backend.repository;

import com.hospital.backend.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, String> {

    boolean existsByEmail(String email);
}
