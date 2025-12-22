package com.hospital.backend.service.impl;

import com.hospital.backend.dto.AfiliacionRequestDTO;
import com.hospital.backend.dto.AfiliacionResponseDTO;
import com.hospital.backend.entity.Afiliacion;
import com.hospital.backend.entity.Cobertura;
import com.hospital.backend.entity.Paciente;
import com.hospital.backend.mapper.AfiliacionMapper;
import com.hospital.backend.repository.AfiliacionRepository;
import com.hospital.backend.repository.CoberturaRepository;
import com.hospital.backend.repository.PacienteRepository;
import com.hospital.backend.service.AfiliacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AfiliacionServiceImpl implements AfiliacionService {

    private final AfiliacionRepository afiliacionRepository;
    private final PacienteRepository pacienteRepository;
    private final CoberturaRepository coberturaRepository;
    private final AfiliacionMapper mapper;




    @Override
    public AfiliacionResponseDTO crearAfiliacion(AfiliacionRequestDTO dto) {

        Paciente paciente = pacienteRepository.findById(dto.getDni())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        Cobertura cobertura = coberturaRepository.findById(dto.getCodigoCobertura())
                .orElseThrow(() -> new RuntimeException("Cobertura no encontrada"));

        // Buscamos la última afiliación activa del paciente
        List<Afiliacion> afiliaciones = afiliacionRepository.findAll()
                .stream()
                .filter(a -> a.getPaciente().getDni().equals(paciente.getDni()))
                .sorted((a1, a2) -> a2.getFechaAlta().compareTo(a1.getFechaAlta()))
                .toList();

        if (!afiliaciones.isEmpty()) {
            Afiliacion ultima = afiliaciones.get(0);
            // Validación: fecha de alta no puede ser anterior a la fecha de baja de la última afiliación
            if (ultima.getFechaBaja() != null && LocalDate.now().isBefore(ultima.getFechaBaja().toLocalDate())) {
                throw new RuntimeException("No se puede crear una afiliación con fecha de alta anterior a la fecha de baja de la última afiliación");
            }

            // Si existe afiliación activa, la damos de baja automáticamente
            if (ultima.isActiva()) {
                ultima.setActiva(false);
                ultima.setFechaBaja(java.sql.Date.valueOf(LocalDate.now()));
                afiliacionRepository.save(ultima);
            }
        }

        Afiliacion afiliacion = new Afiliacion();
        afiliacion.setIdAfiliacion(dto.getIdAfiliacion());
        afiliacion.setPaciente(paciente);
        afiliacion.setCobertura(cobertura);
        afiliacion.setNumeroAfiliado(dto.getNumeroAfiliado());
        afiliacion.setActiva(true);
        afiliacion.setFechaAlta(java.sql.Date.valueOf(LocalDate.now()));

        afiliacionRepository.save(afiliacion);

        return mapper.toResponseDTO(afiliacion);
    }



    @Override
    public List<AfiliacionResponseDTO> listar() {
        return afiliacionRepository.findAll()
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    @Override
    public AfiliacionResponseDTO obtenerPorId(String id) {
        Afiliacion af = afiliacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Afiliación no encontrada"));
        return mapper.toResponseDTO(af);
    }

    @Override
    public void eliminar(String id) {
        afiliacionRepository.deleteById(id);
    }
}
