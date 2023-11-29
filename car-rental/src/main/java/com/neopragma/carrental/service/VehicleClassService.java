package com.neopragma.carrental.service;

import com.neopragma.carrental.model.VehicleClass;
import com.neopragma.carrental.exceptions.VehicleClassNotFoundException;
import com.neopragma.carrental.persistence.VehicleClassRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleClassService {

	private final VehicleClassRepository repository;

	public VehicleClassService(VehicleClassRepository repository) {
		this.repository = repository;
	}

	public List<VehicleClass> findAll() {
		return repository.findAll();
	}

	public VehicleClass save(VehicleClass newVehicleClass) {
		return repository.save(newVehicleClass);
	}

	public VehicleClass findById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new VehicleClassNotFoundException("id", String.valueOf(id)));
	}
	public List<VehicleClass> findAllByName(String name) {
		return repository.findAllByName(name.toLowerCase())
				.orElseThrow(() -> new VehicleClassNotFoundException("name", name));
	}

	public VehicleClass save(VehicleClass newVehicleClass, Long id) {
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

	public void deleteById(Long id) {
		repository.deleteById(id);
	}
}
