package com.neopragma.carrental.exceptions;

public class LocationNotFoundException extends RuntimeException {

	public LocationNotFoundException(String searchName, String searchValue) {
		super("Could not find Location with " + searchName + " <" + searchValue + ">");
	}
}
