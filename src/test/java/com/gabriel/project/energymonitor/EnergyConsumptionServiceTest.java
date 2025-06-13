package com.gabriel.project.energymonitor;

import com.gabriel.project.energymonitor.model.EnergyConsumption;
import com.gabriel.project.energymonitor.repository.EnergyConsumptionRepository;
import com.gabriel.project.energymonitor.service.EnergyConsumptionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EnergyConsumptionServiceTest {

    @Mock
    private EnergyConsumptionRepository repository;

    @Mock
    private KafkaTemplate<String, EnergyConsumption> kafkaTemplate;

    @InjectMocks
    private EnergyConsumptionService service;

    private EnergyConsumption consumption;

    @BeforeEach
    void setUp() {
        consumption = new EnergyConsumption();
        consumption.setId(1L);
        consumption.setDeviceId("device123");
        consumption.setConsumptionKwh(5.2);
        consumption.setRecordedAt(LocalDateTime.now());
    }

    @Test
    void testSave() {
        when(repository.save(any(EnergyConsumption.class))).thenReturn(consumption);
        EnergyConsumption saved = service.save(consumption);

        assertNotNull(saved);
        assertEquals("device123", saved.getDeviceId());
        verify(repository, times(1)).save(consumption);
        verify(kafkaTemplate, times(1)).send("energy-data", consumption.getDeviceId(), consumption);
    }

    @Test
    void testFindById_Success() {
        when(repository.findById(1L)).thenReturn(Optional.of(consumption));
        EnergyConsumption found = service.findById(1L);

        assertNotNull(found);
        assertEquals("device123", found.getDeviceId());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testFindById_NotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        Exception exception = assertThrows(RuntimeException.class, () -> service.findById(1L));
        assertEquals("Energy consumption record not found with id: 1", exception.getMessage());
    }

    @Test
    void testFindAll() {
        List<EnergyConsumption> consumptions = Arrays.asList(consumption);
        when(repository.findAll()).thenReturn(consumptions);
        List<EnergyConsumption> result = service.findAll();

        assertEquals(1, result.size());
        assertEquals("device123", result.get(0).getDeviceId());
        verify(repository, times(1)).findAll();
    }
}
