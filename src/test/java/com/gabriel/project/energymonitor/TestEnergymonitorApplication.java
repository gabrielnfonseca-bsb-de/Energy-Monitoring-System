package com.gabriel.project.energymonitor;

import org.springframework.boot.SpringApplication;

public class TestEnergymonitorApplication {

	public static void main(String[] args) {
		SpringApplication.from(EnergymonitorApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
