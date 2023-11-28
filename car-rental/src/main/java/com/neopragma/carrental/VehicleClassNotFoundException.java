package com.neopragma.carrental;

class VehicleClassNotFoundException extends RuntimeException {

	VehicleClassNotFoundException(Long id) {
		super("Could not find Vehicle Class with id " + id);
	}
}
