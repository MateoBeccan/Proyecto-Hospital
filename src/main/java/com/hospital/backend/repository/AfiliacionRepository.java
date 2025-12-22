package com.hospital.backend.repository;

import com.hospital.backend.entity.Afiliacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AfiliacionRepository extends JpaRepository<Afiliacion, String> {

    // Busca la afiliación activa de un paciente por su DNI
    Optional<Afiliacion> findByPacienteDniAndActivaTrue(String dni);
}
