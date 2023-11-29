package com.neopragma.carrental.service;

import com.neopragma.carrental.model.Location;
import com.neopragma.carrental.exceptions.LocationNotFoundException;
import com.neopragma.carrental.persistence.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {
    @Autowired
    private LocationRepository repository;

    /** called from unit tests */
   public LocationService(LocationRepository repository) {
        this.repository = repository;
    }

    /** called by spring boot */
    public LocationService() {}
    public List<Location> findAll() {
        return repository.findAll();
    }

    public Location findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new LocationNotFoundException("id", String.valueOf(id)));
    }

    public Location findByIata(String iata) {
        return repository.findByIata(iata)
                .orElseThrow(() -> new LocationNotFoundException("iata", iata));

    }
    public List<Location> findAllByCountry(String country) {
        return repository.findAllByCountry(country)
                .orElseThrow(() -> new LocationNotFoundException("country", country));
    }
    public Location save(Location newLocation) {
        return repository.save(newLocation);
    }

    public Location replace(Location newLocation, Long id) {
        return repository.findById(id)
            .map(location -> {
                location.setLanguage(newLocation.getLanguage());
                location.setCountry(newLocation.getCountry());
                location.setState(newLocation.getState());
                location.setCity(newLocation.getCity());
                location.setIata(newLocation.getIata());
                return repository.save(location);
            })
            .orElseGet(() -> {
                newLocation.setId(id);
                return repository.save(newLocation);
            });
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
