package com.gabriel.project.energymonitor.controller;

import com.gabriel.project.energymonitor.model.EnergyConsumption;
import com.gabriel.project.energymonitor.service.EnergyConsumptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/energy")
public class EnergyConsumptionController {
    @Autowired
    private EnergyConsumptionService service;

    @PostMapping
    public EnergyConsumption create(@RequestBody EnergyConsumption consumption) {
        return service.save(consumption);
    }

    @GetMapping("/{id}")
    public EnergyConsumption getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public List<EnergyConsumption> getAll() {
        return service.findAll();
    }
}
