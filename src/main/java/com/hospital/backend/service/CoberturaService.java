package com.hospital.backend.service;

import com.hospital.backend.entity.Cobertura;
import com.hospital.backend.repository.CoberturaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoberturaService {

    private final CoberturaRepository repository;

    public CoberturaService(CoberturaRepository repository) {
        this.repository = repository;
    }

    public List<Cobertura> findAll() {
        return repository.findAll();
    }

    public Cobertura findById(String codigo) {
        return repository.findById(codigo)
                .orElseThrow(() -> new RuntimeException("Cobertura no encontrada"));
    }

    public Cobertura save(Cobertura cobertura) {
        return repository.save(cobertura);
    }

    public Cobertura update(String codigo, Cobertura nueva) {
        Cobertura actual = findById(codigo);

        actual.setNombre(nueva.getNombre());
        actual.setTipo(nueva.getTipo());

        return repository.save(actual);
    }

    public void delete(String codigo) {
        repository.deleteById(codigo);
    }
}
