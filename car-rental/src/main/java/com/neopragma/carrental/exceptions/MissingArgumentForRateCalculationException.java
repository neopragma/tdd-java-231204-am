package com.neopragma.carrental.exceptions;

public class MissingArgumentForRateCalculationException extends RuntimeException {

	public MissingArgumentForRateCalculationException() {
		super("Customer Id, VehicleClass Id, pickup Location Id, and dropoff Location Id are required to calculate rental charges.");
	}
}
