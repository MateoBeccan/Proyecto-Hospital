package com.hospital.backend.service.impl;

import com.hospital.backend.dto.CitaRequestDTO;
import com.hospital.backend.dto.CitaResponseDTO;
import com.hospital.backend.entity.*;
import com.hospital.backend.mapper.CitaMapper;
import com.hospital.backend.repository.*;
import com.hospital.backend.service.CitaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CitaServiceImpl implements CitaService {

    private final HistoriaClinicaRepository historiaClinicaRepository;
    private final CitaRepository repo;
    private final PacienteRepository pacienteRepo;
    private final MedicoRepository medicoRepo;
    private final EstadoCitaRepository estadoRepo;
    private final MedicamentoRepository medicamentoRepo;
    private final CitaMapper mapper;

    @Transactional
    @Override
    public CitaResponseDTO crearCita(CitaRequestDTO dto) {
        // 1. Validar paciente, médico y estado
        Paciente paciente = pacienteRepo.findById(dto.getDniPaciente())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        Medico medico = medicoRepo.findById(dto.getMatriculaMedico())
                .orElseThrow(() -> new RuntimeException("Médico no encontrado"));
        EstadoCita estado = estadoRepo.findById(dto.getCodigoEstado())
                .orElseThrow(() -> new RuntimeException("Estado de cita no encontrado"));

        // 2. Validar disponibilidad del médico
        if (!isMedicoDisponible(medico.getMatricula(), dto.getFechaHora()))
            throw new RuntimeException("El médico ya tiene una cita a esa hora");

        // 3. Crear entidad Cita
        Cita cita = mapper.toEntity(dto);
        cita.setPaciente(paciente);
        cita.setMedico(medico);
        cita.setEstado(estado);
        cita.setTipo(Cita.TipoCita.valueOf(dto.getTipo().toUpperCase()));

        // 4. Guardar la cita primero
        Cita citaGuardada = repo.save(cita);

        // 5. Mapear medicamentos si existen
        if (dto.getMedicamentos() != null) {
            final Cita citaFinal = citaGuardada; // Lambda requiere efectivamente final
            List<CitaMedicamento> meds = dto.getMedicamentos().stream().map(mdto -> {
                CitaMedicamento cm = new CitaMedicamento();

                // Buscar medicamento existente
                Medicamento med = medicamentoRepo.findById(mdto.getCodigoMedicamento())
                        .orElseThrow(() -> new RuntimeException("Medicamento no encontrado: " + mdto.getCodigoMedicamento()));

                // Crear EmbeddedId
                CitaMedicamentoId id = new CitaMedicamentoId(citaFinal.getCodigoCita(), med.getCodigoMedicamento());
                cm.setId(id);

                // Asociar cita y medicamento
                cm.setCita(citaFinal);
                cm.setMedicamento(med);

                // Setear resto de campos
                cm.setDosis(mdto.getDosis());
                cm.setFrecuencia(mdto.getFrecuencia());
                cm.setDuracionTratamiento(mdto.getDuracionTratamiento());
                cm.setObservacion(mdto.getObservacion());
                cm.setFechaPrescripcion(mdto.getFechaPrescripcion());

                return cm;
            }).toList();

            // Agregar a la cita y persistir
            citaFinal.getMedicamentos().addAll(meds);
            citaGuardada = repo.save(citaFinal);
        }
        // 6. Crear registro automático en Historia Clínica
        HistoriaClinica hc = new HistoriaClinica();
        hc.setIdRegistro("HC" + citaGuardada.getCodigoCita()); // Ejemplo: HC-CIT015
        hc.setPaciente(paciente);
        hc.setCita(citaGuardada);
        hc.setFechaRegistro(LocalDate.now());
        hc.setDiagnosticoPrincipal("Consulta médica");
        hc.setObservaciones(citaGuardada.getObservaciones());
        hc.setProcedimientos("Consulta general");
        hc.setResultadosEstudios(null);

// Guardar historia clínica
        historiaClinicaRepository.save(hc);

        return mapper.toResponseDTO(citaGuardada);
    }

    private boolean isMedicoDisponible(String matricula, LocalDateTime fechaHora) {
        return repo.findByMedicoMatriculaAndFechaHora(matricula, fechaHora).isEmpty();
    }

    @Override
    public List<CitaResponseDTO> listarPorPaciente(String dni) {
        return repo.findByPacienteDni(dni).stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CitaResponseDTO> listarPorMedico(String matricula) {
        return repo.findByMedicoMatricula(matricula).stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CitaResponseDTO obtenerPorId(String id) {
        return repo.findById(id)
                .map(mapper::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
    }

    @Override
    @Transactional
    public void eliminarCita(String id) {
        Cita cita = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
        repo.delete(cita);
    }

    @Override
    public List<CitaResponseDTO> filtrarCitas(String dniPaciente,
                                              String matriculaMedico,
                                              String tipo,
                                              String codigoEstado,
                                              LocalDateTime fechaDesde,
                                              LocalDateTime fechaHasta) {

        return repo.findAll().stream()
                .filter(c -> dniPaciente == null || c.getPaciente().getDni().equals(dniPaciente))
                .filter(c -> matriculaMedico == null || c.getMedico().getMatricula().equals(matriculaMedico))
                .filter(c -> tipo == null || c.getTipo().name().equalsIgnoreCase(tipo))
                .filter(c -> codigoEstado == null || c.getEstado().getCodigoEstado().equals(codigoEstado))
                .filter(c -> fechaDesde == null || !c.getFechaHora().isBefore(fechaDesde))
                .filter(c -> fechaHasta == null || !c.getFechaHora().isAfter(fechaHasta))
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public CitaResponseDTO actualizarCita(String id, CitaRequestDTO dto) {

        // 1. Buscar cita existente
        Cita cita = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));

        // 2. Validar paciente, médico y estado
        Paciente paciente = pacienteRepo.findById(dto.getDniPaciente())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        Medico medico = medicoRepo.findById(dto.getMatriculaMedico())
                .orElseThrow(() -> new RuntimeException("Médico no encontrado"));

        EstadoCita estado = estadoRepo.findById(dto.getCodigoEstado())
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));

        // 3. Validar disponibilidad del médico (si cambió fecha o médico)
        if ((!cita.getMedico().getMatricula().equals(dto.getMatriculaMedico())
                || !cita.getFechaHora().equals(dto.getFechaHora()))
                && !isMedicoDisponible(dto.getMatriculaMedico(), dto.getFechaHora())) {
            throw new RuntimeException("El médico ya tiene una cita en esa fecha/hora");
        }

        // 4. Actualizar datos principales
        cita.setFechaHora(dto.getFechaHora());
        cita.setPaciente(paciente);
        cita.setMedico(medico);
        cita.setEstado(estado);
        cita.setObservaciones(dto.getObservaciones());
        cita.setTipo(Cita.TipoCita.valueOf(dto.getTipo().toUpperCase()));

        // 5. LIMPIAR medicamentos previos
        cita.getMedicamentos().clear();

        // 6. Volver a agregarlos si existen
        if (dto.getMedicamentos() != null) {
            for (var mdto : dto.getMedicamentos()) {

                Medicamento med = medicamentoRepo.findById(mdto.getCodigoMedicamento())
                        .orElseThrow(() -> new RuntimeException("Medicamento no encontrado"));

                CitaMedicamento cm = new CitaMedicamento();

                cm.setId(new CitaMedicamentoId(
                        cita.getCodigoCita(),
                        med.getCodigoMedicamento()
                ));

                cm.setCita(cita);
                cm.setMedicamento(med);
                cm.setDosis(mdto.getDosis());
                cm.setFrecuencia(mdto.getFrecuencia());
                cm.setDuracionTratamiento(mdto.getDuracionTratamiento());
                cm.setObservacion(mdto.getObservacion());
                cm.setFechaPrescripcion(mdto.getFechaPrescripcion());

                cita.getMedicamentos().add(cm);
            }
        }

        // 7. Guardar y retornar
        repo.save(cita);

        return mapper.toResponseDTO(cita);
    }

}
