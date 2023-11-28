package com.neopragma.carrental;

class AirportNotFoundException extends RuntimeException {

	AirportNotFoundException(Long id) {
		super("Could not find an Airport with id " + id);
	}
}
