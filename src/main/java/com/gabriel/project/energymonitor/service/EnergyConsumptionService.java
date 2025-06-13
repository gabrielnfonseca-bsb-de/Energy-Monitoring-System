package com.gabriel.project.energymonitor.service;

import com.gabriel.project.energymonitor.model.EnergyConsumption;
import com.gabriel.project.energymonitor.repository.EnergyConsumptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnergyConsumptionService {
    @Autowired
    private EnergyConsumptionRepository repository;

    @Autowired
    private KafkaTemplate<String, EnergyConsumption> kafkaTemplate;

    public EnergyConsumption save(EnergyConsumption consumption) {
        EnergyConsumption saved = repository.save(consumption);
        kafkaTemplate.send("energy-data", saved.getDeviceId(), saved);
        return saved;
    }

    public EnergyConsumption findById(Long id) {
        Optional<EnergyConsumption> consumption = repository.findById(id);
        return consumption.orElseThrow(() -> new RuntimeException("Energy consumption record not found with id: " + id));
    }

    public List<EnergyConsumption> findAll() {
        return repository.findAll();
    }
}