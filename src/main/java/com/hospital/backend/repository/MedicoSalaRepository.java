package com.hospital.backend.repository;

import com.hospital.backend.entity.MedicoSala;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MedicoSalaRepository extends JpaRepository<MedicoSala, String> {

    List<MedicoSala> findByMedicoMatricula(String matricula);

    List<MedicoSala> findBySalaCodigoSala(String codigoSala);

    boolean existsByMedicoMatriculaAndSalaCodigoSalaAndActivaTrue(
            String matricula, String codigoSala);

    Optional<MedicoSala> findByMedicoMatriculaAndSalaCodigoSala(String matricula, String codigoSala);

}

