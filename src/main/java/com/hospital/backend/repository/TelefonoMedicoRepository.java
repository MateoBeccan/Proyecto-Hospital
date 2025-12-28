package com.hospital.backend.repository;

import com.hospital.backend.entity.TelefonoMedico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TelefonoMedicoRepository extends JpaRepository<TelefonoMedico, String> {

    List<TelefonoMedico> findByMedicoMatricula(String matricula);

    Optional<TelefonoMedico> findByMedicoMatriculaAndEsPrincipalTrue(String matricula);
}
