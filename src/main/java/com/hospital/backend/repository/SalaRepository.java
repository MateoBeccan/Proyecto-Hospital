package com.hospital.backend.repository;

import com.hospital.backend.entity.Sala;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalaRepository extends JpaRepository<Sala, String> {

    List<Sala> findByCodigoDepartamento(String codigoDepartamento);

    List<Sala> findByTipo(Sala.TipoSala tipo);

    List<Sala> findByEstado(Sala.EstadoSala estado);
}
