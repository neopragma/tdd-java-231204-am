package com.neopragma.carrental;

class CustomerNotFoundException extends RuntimeException {

	CustomerNotFoundException(Long id) {
		super("Could not find Customer with id " + id);
	}
}
