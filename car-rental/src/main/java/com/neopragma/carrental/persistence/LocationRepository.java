package com.neopragma.carrental.persistence;

import com.neopragma.carrental.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {
    public Optional<List<Location>> findAllByCountry(String country);
    public Optional<Location> findByIata(String iata);

}
