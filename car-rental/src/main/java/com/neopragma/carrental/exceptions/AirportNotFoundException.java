package com.neopragma.carrental.exceptions;

public class AirportNotFoundException extends RuntimeException {

	public AirportNotFoundException(Long id) {
		super("Could not find an Airport with id " + id);
	}
}
