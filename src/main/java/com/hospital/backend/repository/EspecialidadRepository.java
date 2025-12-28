package com.hospital.backend.repository;

import com.hospital.backend.entity.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EspecialidadRepository extends JpaRepository<Especialidad, String> {

    boolean existsByNombreIgnoreCase(String nombre);
}
