package com.neopragma.carrental.controller;

import com.neopragma.carrental.model.Airport;
import com.neopragma.carrental.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class AirportController {

	@Autowired
	private AirportService service;

	@GetMapping("/airport")
	public CollectionModel<EntityModel<Airport>> all() {
		List<EntityModel<Airport>> airports = service.findAll().stream()
				.map(airport -> EntityModel.of(airport,
						linkTo(methodOn(AirportController.class).one(airport.getId())).withSelfRel(),
						linkTo(methodOn(AirportController.class).all()).withRel("airports")))
				.collect(Collectors.toList());

		return CollectionModel.of(airports, linkTo(methodOn(AirportController.class).all()).withSelfRel());
	}

	@PostMapping("/airport")
	public Airport newAirport(@RequestBody Airport newAirport) {
		return service.save(newAirport);
	}

	@GetMapping("/airport/{id}")
	public EntityModel<Airport> one(@PathVariable("id") Long id) {
		Airport airport = service.findById(id);
		return EntityModel.of(airport,
				linkTo(methodOn(AirportController.class).one(id)).withSelfRel(),
				linkTo(methodOn(AirportController.class).all()).withRel("airports"));
	}

	@PutMapping("/airport/{id}")
	public Airport replaceAirport(@RequestBody Airport newAirport, @PathVariable Long id) {
        return service.replace(newAirport, id);
	}

	@DeleteMapping("/airport/{id}")
	public void deleteAirport(@PathVariable Long id) {
		service.deleteById(id);
	}
}
