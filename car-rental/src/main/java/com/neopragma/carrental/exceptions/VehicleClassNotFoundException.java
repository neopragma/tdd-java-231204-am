package com.neopragma.carrental.exceptions;

public class VehicleClassNotFoundException extends RuntimeException {

	public VehicleClassNotFoundException(String searchName, String searchValue) {
		super("Could not find VehicleClass with " + searchName + " <" + searchValue + ">");
	}
}
