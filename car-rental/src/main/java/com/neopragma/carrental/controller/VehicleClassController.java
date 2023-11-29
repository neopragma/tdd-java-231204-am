package com.neopragma.carrental.controller;

import com.neopragma.carrental.model.VehicleClass;
import com.neopragma.carrental.service.VehicleClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class VehicleClassController {

	@Autowired
	private VehicleClassService service;

	@GetMapping("/vehicleClass")
	public CollectionModel<EntityModel<VehicleClass>> all() {
		List<EntityModel<VehicleClass>> vehicleClasses = service.findAll().stream()
				.map(vehicleClass -> EntityModel.of(vehicleClass,
						linkTo(methodOn(VehicleClassController.class).one(vehicleClass.getId())).withSelfRel(),
						linkTo(methodOn(VehicleClassController.class).all()).withRel("vehicleClasses")))
				.collect(Collectors.toList());

		return CollectionModel.of(vehicleClasses, linkTo(methodOn(VehicleClassController.class).all()).withSelfRel());
	}

	@PostMapping("/vehicleClass")
	public VehicleClass newVehicleclass(@RequestBody VehicleClass newVehicleClass) {
		return service.save(newVehicleClass);
	}

	@GetMapping("/vehicleClass/{id}")
	public EntityModel<VehicleClass> one(@PathVariable("id") Long id) {
		VehicleClass vehicleClass = service.findById(id);
		return EntityModel.of(vehicleClass,
				linkTo(methodOn(VehicleClassController.class).one(id)).withSelfRel(),
				linkTo(methodOn(VehicleClassController.class).all()).withRel("vehicleClasses"));
	}
	@GetMapping("/vehicleClass/name/{name}")
	public CollectionModel<EntityModel<VehicleClass>> allByName(@PathVariable("name") String name) {
		List<EntityModel<VehicleClass>> vehicleClasses = service.findAllByName(name.toLowerCase()).stream()
				.map(vehicleClass -> EntityModel.of(vehicleClass,
						linkTo(methodOn(VehicleClassController.class).all()).withRel("vehicleClasses")))
				.collect(Collectors.toList());

		return CollectionModel.of(vehicleClasses, linkTo(methodOn(VehicleClassController.class).all()).withSelfRel());
	}

	@PutMapping("/vehicleClass/{id}")
	public VehicleClass replaceVehicleClass(@RequestBody VehicleClass newVehicleClass, @PathVariable Long id) {
		return service.save(newVehicleClass, id);
	}

	@DeleteMapping("/vehicleClass/{id}")
	public void deleteVehicleClass(@PathVariable Long id) {
		service.deleteById(id);
	}
}
