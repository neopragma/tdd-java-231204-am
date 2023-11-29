package com.neopragma.carrental.persistence;

import com.neopragma.carrental.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Long> {

}
