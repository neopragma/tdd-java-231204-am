package com.neopragma.carrental.service;

import com.neopragma.carrental.model.Airport;
import com.neopragma.carrental.exceptions.AirportNotFoundException;
import com.neopragma.carrental.persistence.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Service
public class AirportService {

	@Autowired
	private final AirportRepository repository;

	public AirportService(AirportRepository repository) {
		this.repository = repository;
	}

	public List<Airport> findAll() {
        return repository.findAll();
	}

	public Airport save(Airport newAirport) {
		return repository.save(newAirport);
	}

	public Airport findById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new AirportNotFoundException(id));
	}

	public Airport replace(Airport newAirport, Long id) {
		return repository.findById(id)
				.map(airport -> {
					airport.setName(newAirport.getName());
					airport.setIata(newAirport.getIata());
					airport.setAirportFee(newAirport.getAirportFee());
					return repository.save(airport);
				}) //
				.orElseGet(() -> {
					newAirport.setId(id);
					return repository.save(newAirport);
				});
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}
}
