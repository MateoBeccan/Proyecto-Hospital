package com.hospital.backend.repository;

import com.hospital.backend.entity.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicamentoRepository extends JpaRepository<Medicamento, String> {
}
