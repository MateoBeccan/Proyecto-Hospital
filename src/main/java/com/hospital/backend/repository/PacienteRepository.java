package com.hospital.backend.repository;

import com.hospital.backend.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, String> {

    boolean existsByEmail(String email);
}
