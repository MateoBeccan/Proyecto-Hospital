package com.hospital.backend.repository;

import com.hospital.backend.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicoRepository extends JpaRepository<Medico, String> {

    Optional<Medico> findByDni(String dni);
}
