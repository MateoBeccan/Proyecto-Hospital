package com.hospital.backend.repository;

import com.hospital.backend.entity.HistoriaClinica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoriaClinicaRepository extends JpaRepository<HistoriaClinica, String> {

    List<HistoriaClinica> findByPaciente_Dni(String dni);
    List<HistoriaClinica> findByCita_CodigoCita(String codigoCita);
}
