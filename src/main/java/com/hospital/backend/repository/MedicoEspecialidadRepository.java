package com.hospital.backend.repository;

import com.hospital.backend.entity.MedicoEspecialidad;
import com.hospital.backend.entity.MedicoEspecialidadId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicoEspecialidadRepository
        extends JpaRepository<MedicoEspecialidad, MedicoEspecialidadId> {

    boolean existsById(MedicoEspecialidadId id);

    List<MedicoEspecialidad> findByMedicoMatricula(String matricula);

    List<MedicoEspecialidad> findByEspecialidadCodigoEspecialidad(String codigo);
}
