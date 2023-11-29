package com.neopragma.carrental.controller;

import com.neopragma.carrental.model.Customer;
import com.neopragma.carrental.model.Location;
import com.neopragma.carrental.service.CustomerService;
import com.neopragma.carrental.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class LocationController {

	@Autowired
	private LocationService service;

	@GetMapping("/location")
	public CollectionModel<EntityModel<Location>> all() {
		List<EntityModel<Location>> locations = service.findAll().stream()
				.map(location -> EntityModel.of(location,
						linkTo(methodOn(LocationController.class).one(location.getId())).withSelfRel(),
						linkTo(methodOn(LocationController.class).all()).withRel("locations")))
				.collect(Collectors.toList());

		return CollectionModel.of(locations, linkTo(methodOn(LocationController.class).all()).withSelfRel());
	}

	@PostMapping("/location")
	public Location newLocation(@RequestBody Location newLocation) {
		return service.save(newLocation);
	}

	@GetMapping("/location/{id}")
	public EntityModel<Location> one(@PathVariable("id") Long id) {
		Location location = service.findById(id);
		return EntityModel.of(location,
				linkTo(methodOn(LocationController.class).one(id)).withSelfRel(),
				linkTo(methodOn(LocationController.class).all()).withRel("locations"));
	}
	@GetMapping("/location/airport/{iata}")
	public EntityModel<Location> oneByIata(@PathVariable("iata") String iata) {
		Location location = service.findByIata(iata.toUpperCase());
		return EntityModel.of(location,
				linkTo(methodOn(LocationController.class).oneByIata(iata)).withSelfRel(),
				linkTo(methodOn(LocationController.class).all()).withRel("locations"));
	}
	@GetMapping("/location/country/{country}")
	public CollectionModel<EntityModel<Location>> allByCountry(@PathVariable("country") String country) {
		List<EntityModel<Location>> locations = service.findAllByCountry(country.toUpperCase()).stream()
				.map(location -> EntityModel.of(location,
						linkTo(methodOn(LocationController.class).all()).withRel("locations")))
				.collect(Collectors.toList());

		return CollectionModel.of(locations, linkTo(methodOn(LocationController.class).all()).withSelfRel());
	}

	@PutMapping("/location/{id}")
	public Location replaceLocation(@RequestBody Location newLocation, @PathVariable Long id) {
		return service.replace(newLocation, id);
	}

	@DeleteMapping("/location/{id}")
	public void deleteLocation(@PathVariable Long id) {
		service.deleteById(id);
	}
}
