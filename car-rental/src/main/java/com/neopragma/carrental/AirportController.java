package com.neopragma.carrental;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
class AirportController {

	private final AirportRepository repository;

	AirportController(AirportRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/airport")
	CollectionModel<EntityModel<Airport>> all() {

		List<EntityModel<Airport>> airports = repository.findAll().stream()
				.map(airport -> EntityModel.of(airport,
						linkTo(methodOn(AirportController.class).one(airport.getId())).withSelfRel(),
						linkTo(methodOn(AirportController.class).all()).withRel("airports")))
				.collect(Collectors.toList());

		return CollectionModel.of(airports, linkTo(methodOn(AirportController.class).all()).withSelfRel());
	}

	@PostMapping("/airport")
	Airport newAirport(@RequestBody Airport newAirport) {
		return repository.save(newAirport);
	}

	@GetMapping("/airport/{id}")
	EntityModel<Airport> one(@PathVariable("id") Long id) {

		Airport airport = repository.findById(id) //
				.orElseThrow(() -> new AirportNotFoundException(id));

		return EntityModel.of(airport,
				linkTo(methodOn(AirportController.class).one(id)).withSelfRel(),
				linkTo(methodOn(AirportController.class).all()).withRel("airports"));
	}

	@PutMapping("/airport/{id}")
	Airport replaceAirport(@RequestBody Airport newAirport, @PathVariable Long id) {

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

	@DeleteMapping("/airport/{id}")
	void deleteAirport(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
