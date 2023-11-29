package com.neopragma.carrental.persistence;

import com.neopragma.carrental.model.VehicleClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehicleClassRepository extends JpaRepository<VehicleClass, Long> {

    public Optional<List<VehicleClass>> findAllByName(String name);

}
