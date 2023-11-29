package com.neopragma.carrental.persistence;

import com.neopragma.carrental.model.LocationAndVehicleClassKey;

public class BaseDailyRentalRateNotFoundException extends RuntimeException {

	public BaseDailyRentalRateNotFoundException(LocationAndVehicleClassKey key) {
		super("Could not find a BaseDailyRentalRate for location " +
				key.getLocationId() + " and vehicleClass " + key.getVehicleClassId());
	}
}
