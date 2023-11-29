package com.neopragma.carrental.persistence;

import com.neopragma.carrental.model.BaseDailyRentalRate;
import com.neopragma.carrental.model.LocationAndVehicleClassKey;
import org.springframework.data.jpa.repository.JpaRepository;

//public interface BaseDailyRentalRateRepository extends JpaRepository<BaseDailyRentalRate, Long> { }
public interface BaseDailyRentalRateRepository extends JpaRepository<BaseDailyRentalRate, LocationAndVehicleClassKey> { }
