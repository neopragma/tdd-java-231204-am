package com.neopragma.carrental.controller;

import com.neopragma.carrental.model.BaseDailyRentalRate;
import com.neopragma.carrental.model.LocationAndVehicleClassKey;
import com.neopragma.carrental.service.BaseDailyRentalRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class BaseDailyRentalRateController {
	@Autowired
	private BaseDailyRentalRateService service;

	@GetMapping("/baseDailyRentalRate")
	public CollectionModel<EntityModel<BaseDailyRentalRate>> all() {
		List<EntityModel<BaseDailyRentalRate>> baseDailyRentalRates = service.findAll().stream()
				.map(baseDailyRentalRate -> EntityModel.of(baseDailyRentalRate,
						linkTo(methodOn(BaseDailyRentalRateController.class)
								.one(baseDailyRentalRate.getId().getLocationId(),
									 baseDailyRentalRate.getId().getVehicleClassId()))
								.withSelfRel(),
						linkTo(methodOn(BaseDailyRentalRateController.class)
								.all())
								.withRel("baseDailyRentalRates")))
				.collect(Collectors.toList());

		return CollectionModel.of(baseDailyRentalRates, linkTo(methodOn(BaseDailyRentalRateController.class).all()).withSelfRel());
	}

	@PostMapping("/baseDailyRentalRate")
	public BaseDailyRentalRate newBaseDailyRentalRate(@RequestBody BaseDailyRentalRate newBaseDailyRentalRate) {
		return service.save(newBaseDailyRentalRate);
	}

	@GetMapping("/baseDailyRentalRate/location/{locationId}/vehicleClass/{vehicleClassId}")
	public EntityModel<BaseDailyRentalRate> one(
			@PathVariable("locationId") Long locationId,
			@PathVariable("vehicleClassId") Long vehicleClassId) {
		LocationAndVehicleClassKey id = new LocationAndVehicleClassKey(locationId, vehicleClassId);
		BaseDailyRentalRate baseDailyRentalRate = service.findById(id);
		return EntityModel.of(baseDailyRentalRate,
				linkTo(methodOn(BaseDailyRentalRateController.class).one(locationId, vehicleClassId)).withSelfRel(),
				linkTo(methodOn(BaseDailyRentalRateController.class).all()).withRel("baseDailyRentalRates"));
	}

	@PutMapping("/baseDailyRentalRate/location/{locationId}/vehicleClass/{vehicleClassId}")
	public BaseDailyRentalRate replaceBaseDailyRentalRate(
			@RequestBody BaseDailyRentalRate newBaseDailyRentalRate,
			@PathVariable("locationId") Long locationId,
			@PathVariable("vehicleClassId") Long vehicleClassId) {
		LocationAndVehicleClassKey id = new LocationAndVehicleClassKey(locationId, vehicleClassId);
		return service.replace(newBaseDailyRentalRate, id);
	}

	@DeleteMapping("/baseDailyRentalRate/location/{locationId}/vehicleClass/{vehicleClassId}")
	public void deleteLocation(
			@PathVariable("locationId") Long locationId,
			@PathVariable("vehicleClassId") Long vehicleClassId) {
		LocationAndVehicleClassKey id = new LocationAndVehicleClassKey(locationId, vehicleClassId);
		service.deleteById(id);
	}
}
