package com.neopragma.carrental.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CarRentalConfig {

    @Bean
    public AvailableVehicles availableVehicles() {
        return new AvailableVehicles();
    }

    @Bean
    public AvailableVehiclesQueryResult availableVehiclesQueryResult() {
        return new AvailableVehiclesQueryResult(0, null);
    }

    @Bean
    public InventoryController inventoryController() {
        return new InventoryController(new AvailableVehicles());
    }
}
