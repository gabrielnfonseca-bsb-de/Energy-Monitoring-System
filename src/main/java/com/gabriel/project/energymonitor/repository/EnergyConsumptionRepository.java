package com.gabriel.project.energymonitor.repository;

import com.gabriel.project.energymonitor.model.EnergyConsumption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnergyConsumptionRepository extends JpaRepository<EnergyConsumption, Long> {
}