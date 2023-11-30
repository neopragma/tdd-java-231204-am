package com.neopragma.carrental.service;

import com.neopragma.carrental.model.BaseDailyRentalRate;
import com.neopragma.carrental.model.LocationAndVehicleClassKey;
import com.neopragma.carrental.exceptions.BaseDailyRentalRateNotFoundException;
import com.neopragma.carrental.persistence.BaseDailyRentalRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseDailyRentalRateService {
    @Autowired
    private BaseDailyRentalRateRepository repository;

    /** called from unit tests */
   public BaseDailyRentalRateService(BaseDailyRentalRateRepository repository) {
        this.repository = repository;
    }

    /** called by spring boot */
    public BaseDailyRentalRateService() {}
    public List<BaseDailyRentalRate> findAll() {
        return repository.findAll();
    }

    public BaseDailyRentalRate findById(LocationAndVehicleClassKey id) {
        return repository.findById(id)
                .orElseThrow(() -> new BaseDailyRentalRateNotFoundException(id));
    }

    public BaseDailyRentalRate save(BaseDailyRentalRate newBaseDailyRentalRate) {
        return repository.save(newBaseDailyRentalRate);
    }

    public BaseDailyRentalRate replace(BaseDailyRentalRate newBaseDailyRentalRate, LocationAndVehicleClassKey id) {
        return repository.findById(id)
            .map(baseDailyRentalRate -> {
                baseDailyRentalRate.setBaseDailyRate(newBaseDailyRentalRate.getBaseDailyRate());
                return repository.save(baseDailyRentalRate);
            })
            .orElseGet(() -> {
                newBaseDailyRentalRate.setId(id);
                return repository.save(newBaseDailyRentalRate);
            });
    }

    public void deleteById(LocationAndVehicleClassKey id) {
        repository.deleteById(id);
    }
}
