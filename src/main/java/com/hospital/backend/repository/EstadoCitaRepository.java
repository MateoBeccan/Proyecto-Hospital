package com.hospital.backend.repository;

import com.hospital.backend.entity.EstadoCita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoCitaRepository extends JpaRepository<EstadoCita, String> {
}
