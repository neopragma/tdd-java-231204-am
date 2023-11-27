package com.neopragma.carrental.service;

import com.neopragma.carrental.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class InventoryController {

	@Autowired AvailableVehicles availableVehicles;

	InventoryController(AvailableVehicles availableVehicles)
	{
		this.availableVehicles = availableVehicles;
	}

	@GetMapping(value = {
			"/availableVehicles/{countryCode}/{cityNameOrAirportCode}/{vehicleClass}/{power}/{transmission}",
			"/availableVehicles/{countryCode}/{cityNameOrAirportCode}/{vehicleClass}/{power}",
			"/availableVehicles/{countryCode}/{cityNameOrAirportCode}/{vehicleClass}",
			"/availableVehicles/{countryCode}/{cityNameOrAirportCode}",
			"/availableVehicles/{countryCode}/{cityNameOrAirportCode}",
			"/availableVehicles/{countryCode}",
			"/availableVehicles"
	})
	public Map<String, String> availableVehicles(@PathVariable Map<String, String> pathVarsMap) {
        String countryCode = Strings.isEmpty(pathVarsMap.get("countryCode"))
				? null
				: pathVarsMap.get("countryCode");
        String cityNameOrAirportCode = Strings.isEmpty(pathVarsMap.get("cityNameOrAirportCode"))
				? null
				: pathVarsMap.get("cityNameOrAirportCode");
        String vehicleClass = Strings.isEmpty(pathVarsMap.get("vehicleClass"))
				? null
				: pathVarsMap.get("vehicleClass");
        String power = Strings.isEmpty(pathVarsMap.get("power"))
				? null
				: pathVarsMap.get("power");
        String transmission = Strings.isEmpty(pathVarsMap.get("transmission"))
				? null
				: pathVarsMap.get("transmission");

		if (Strings.isNotEmpty(cityNameOrAirportCode)) {
			if (cityNameOrAirportCode.matches("(?<![A-Z])[A-Z]{3}(?![A-Z])")) {
				availableVehicles.setCityName(null);
				availableVehicles.setAirportCode(cityNameOrAirportCode);
			} else {
				availableVehicles.setCityName(cityNameOrAirportCode);
				availableVehicles.setAirportCode(null);
			}
		} else if (Strings.isEmpty(countryCode)) {
			availableVehicles.setCountryCode(null);
			availableVehicles.setMessage("Either airportCode or both countryCode and cityName must be provided.");
		}
		availableVehicles.setCountryCode(countryCode);
		availableVehicles.setVehicleType(vehicleClass);
		availableVehicles.setPowerType(power);
		availableVehicles.setTransmission(transmission);
		HashMap<String, String> map = new HashMap<>();
		map.put("countryCode", availableVehicles.getCountryCode());
		map.put("cityName", availableVehicles.getCityName());
		map.put("airportCode", availableVehicles.getAirportCode());
		map.put("vehicleClass", availableVehicles.getVehicleType());
		map.put("power", availableVehicles.getPowerType());
		map.put("transmission", availableVehicles.getTransmission());
		AvailableVehiclesQueryResult qr = availableVehicles.queryAvailableVehicleCount();
		map.put("message", qr.message());
		map.put("count", String.valueOf(qr.count()));
		return map;
	}
}
