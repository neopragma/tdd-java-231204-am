package com.neopragma.carrental;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
class VehicleClassController {

	private final VehicleClassRepository repository;

	VehicleClassController(VehicleClassRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/vehicleClass")
	CollectionModel<EntityModel<VehicleClass>> all() {

		List<EntityModel<VehicleClass>> vehicleClasses = repository.findAll().stream()
				.map(vehicleClass -> EntityModel.of(vehicleClass,
						linkTo(methodOn(VehicleClassController.class).one(vehicleClass.getId())).withSelfRel(),
						linkTo(methodOn(VehicleClassController.class).all()).withRel("vehicleClasses")))
				.collect(Collectors.toList());

		return CollectionModel.of(vehicleClasses, linkTo(methodOn(VehicleClassController.class).all()).withSelfRel());
	}

	@PostMapping("/vehicleClass")
	VehicleClass newVehicleclass(@RequestBody VehicleClass newVehicleClass) {
		return repository.save(newVehicleClass);
	}

	@GetMapping("/vehicleClass/{id}")
	EntityModel<VehicleClass> one(@PathVariable("id") Long id) {

		VehicleClass vehicleClass = repository.findById(id) //
				.orElseThrow(() -> new VehicleClassNotFoundException(id));

		return EntityModel.of(vehicleClass,
				linkTo(methodOn(VehicleClassController.class).one(id)).withSelfRel(),
				linkTo(methodOn(VehicleClassController.class).all()).withRel("vehicleClasses"));
	}

	@PutMapping("/vehicleClass/{id}")
	VehicleClass replaceVehicleClass(@RequestBody VehicleClass newVehicleClass, @PathVariable Long id) {

		return repository.findById(id)
				.map(vehicleClass -> {
					vehicleClass.setName(newVehicleClass.getName());
					return repository.save(vehicleClass);
				}) //
				.orElseGet(() -> {
					newVehicleClass.setId(id);
					return repository.save(newVehicleClass);
				});
	}

	@DeleteMapping("/vehicleClass/{id}")
	void deleteVehicleClass(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
