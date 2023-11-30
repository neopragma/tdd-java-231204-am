package com.neopragma.carrental.controller;

import com.neopragma.carrental.exceptions.MissingArgumentForRateCalculationException;
import com.neopragma.carrental.model.VehicleClass;
import com.neopragma.carrental.service.RateCalculationService;
import com.neopragma.carrental.service.VehicleClassService;
import com.neopragma.carrental.util.Strings;
import org.javamoney.moneta.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class RateCalculationController {
	@Autowired
	private RateCalculationService service;

	@GetMapping("/rate")
	public ResponseEntity<Map<String,String>> defaultResponse() {
		Map<String,String> data = new HashMap<>();
		data.put("uriPattern","/rate/customer/{customerId}/vehicle/{vehicleClassId}/pickup/{pickupLocationId}/dropoff/{dropoffLocationId}/period/{rentalPeriod}/passengers/{passengers}/mileage/{estimatedMileage}/insurance/{insuranceOption}/claims24/{claims24}/claims36/{claims36}/referredBy/{referredBy}");
		data.put("uriSample","/rate/customer/435/vehicle/3/pickup/14/dropoff/14/period/daily/passengers/2/mileage/250/insurance/full%20coverage/claims24/0/claims36/{0}/referredBy/837-426-T");
		data.put("description","URI help for rental charge calculation");
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	@GetMapping({
			"/rate/customer/{customerId}/vehicle/{vehicleClassId}/pickup/{pickupLocationId}/dropoff/{dropoffLocationId}/period/{rentalPeriod}/passengers/{passengers}/mileage/{estimatedMileage}/insurance/{insuranceOption}/claims24/{claims24}/claims36/{claims36}/referredBy/{referredBy}",
			"/rate/customer/{customerId}/vehicle/{vehicleClassId}/pickup/{pickupLocationId}/dropoff/{dropoffLocationId}/period/{rentalPeriod}/passengers/{passengers}/mileage/{estimatedMileage}/insurance/{insuranceOption}/claims24/{claims24}/claims36/{claims36}",
			"/rate/customer/{customerId}/vehicle/{vehicleClassId}/pickup/{pickupLocationId}/dropoff/{dropoffLocationId}/period/{rentalPeriod}/passengers/{passengers}/mileage/{estimatedMileage}/insurance/{insuranceOption}/claims24/{claims24}",
			"/rate/customer/{customerId}/vehicle/{vehicleClassId}/pickup/{pickupLocationId}/dropoff/{dropoffLocationId}/period/{rentalPeriod}/passengers/{passengers}/mileage/{estimatedMileage}/insurance/{insuranceOption}",
			"/rate/customer/{customerId}/vehicle/{vehicleClassId}/pickup/{pickupLocationId}/dropoff/{dropoffLocationId}/period/{rentalPeriod}/passengers/{passengers}/mileage/{estimatedMileage}",
			"/rate/customer/{customerId}/vehicle/{vehicleClassId}/pickup/{pickupLocationId}/dropoff/{dropoffLocationId}/period/{rentalPeriod}/passengers/{passengers}",
			"/rate/customer/{customerId}/vehicle/{vehicleClassId}/pickup/{pickupLocationId}/dropoff/{dropoffLocationId}/period/{rentalPeriod}",
			"/rate/customer/{customerId}/vehicle/{vehicleClassId}/pickup/{pickupLocationId}/dropoff/{dropoffLocationId}",
			"/rate/customer/{customerId}/vehicle/{vehicleClassId}/pickup/{pickupLocationId}/dropoff/{dropoffLocationId}/passengers/{passengers}/mileage/{estimatedMileage}/insurance/{insuranceOption}/claims24/{claims24}/claims36/{claims36}/referredBy/{referredBy}",
			"/rate/customer/{customerId}/vehicle/{vehicleClassId}/pickup/{pickupLocationId}/dropoff/{dropoffLocationId}/passengers/{passengers}/mileage/{estimatedMileage}/insurance/{insuranceOption}/claims24/{claims24}/claims36/{claims36}",
			"/rate/customer/{customerId}/vehicle/{vehicleClassId}/pickup/{pickupLocationId}/dropoff/{dropoffLocationId}/passengers/{passengers}/mileage/{estimatedMileage}/insurance/{insuranceOption}/claims24/{claims24}",
			"/rate/customer/{customerId}/vehicle/{vehicleClassId}/pickup/{pickupLocationId}/dropoff/{dropoffLocationId}/passengers/{passengers}/mileage/{estimatedMileage}/insurance/{insuranceOption}",
			"/rate/customer/{customerId}/vehicle/{vehicleClassId}/pickup/{pickupLocationId}/dropoff/{dropoffLocationId}/passengers/{passengers}/mileage/{estimatedMileage}",
			"/rate/customer/{customerId}/vehicle/{vehicleClassId}/pickup/{pickupLocationId}/dropoff/{dropoffLocationId}/passengers/{passengers}",
			"/rate/customer/{customerId}/vehicle/{vehicleClassId}/pickup/{pickupLocationId}/dropoff/{dropoffLocationId}/period/{rentalPeriod}/mileage/{estimatedMileage}/insurance/{insuranceOption}/claims24/{claims24}/claims36/{claims36}/referredBy/{referredBy}",
			"/rate/customer/{customerId}/vehicle/{vehicleClassId}/pickup/{pickupLocationId}/dropoff/{dropoffLocationId}/period/{rentalPeriod}/mileage/{estimatedMileage}/insurance/{insuranceOption}/claims24/{claims24}/claims36/{claims36}",
			"/rate/customer/{customerId}/vehicle/{vehicleClassId}/pickup/{pickupLocationId}/dropoff/{dropoffLocationId}/period/{rentalPeriod}/mileage/{estimatedMileage}/insurance/{insuranceOption}/claims24/{claims24}",
			"/rate/customer/{customerId}/vehicle/{vehicleClassId}/pickup/{pickupLocationId}/dropoff/{dropoffLocationId}/period/{rentalPeriod}/mileage/{estimatedMileage}/insurance/{insuranceOption}",
			"/rate/customer/{customerId}/vehicle/{vehicleClassId}/pickup/{pickupLocationId}/dropoff/{dropoffLocationId}/period/{rentalPeriod}/mileage/{estimatedMileage}",
			"/rate/customer/{customerId}/vehicle/{vehicleClassId}/pickup/{pickupLocationId}/dropoff/{dropoffLocationId}/period/{rentalPeriod}/passengers/{passengers}/insurance/{insuranceOption}/claims24/{claims24}/claims36/{claims36}/referredBy/{referredBy}",
			"/rate/customer/{customerId}/vehicle/{vehicleClassId}/pickup/{pickupLocationId}/dropoff/{dropoffLocationId}/period/{rentalPeriod}/passengers/{passengers}/insurance/{insuranceOption}/claims24/{claims24}/claims36/{claims36}",
			"/rate/customer/{customerId}/vehicle/{vehicleClassId}/pickup/{pickupLocationId}/dropoff/{dropoffLocationId}/period/{rentalPeriod}/passengers/{passengers}/insurance/{insuranceOption}/claims24/{claims24}",
			"/rate/customer/{customerId}/vehicle/{vehicleClassId}/pickup/{pickupLocationId}/dropoff/{dropoffLocationId}/period/{rentalPeriod}/passengers/{passengers}/insurance/{insuranceOption}",
			"/rate/customer/{customerId}/vehicle/{vehicleClassId}/pickup/{pickupLocationId}/dropoff/{dropoffLocationId}/period/{rentalPeriod}/passengers/{passengers}",
			"/rate/customer/{customerId}/vehicle/{vehicleClassId}/pickup/{pickupLocationId}/dropoff/{dropoffLocationId}/period/{rentalPeriod}/insurance/{insuranceOption}/claims24/{claims24}/claims36/{claims36}/referredBy/{referredBy}",
			"/rate/customer/{customerId}/vehicle/{vehicleClassId}/pickup/{pickupLocationId}/dropoff/{dropoffLocationId}/period/{rentalPeriod}/insurance/{insuranceOption}/claims24/{claims24}/claims36/{claims36}",
			"/rate/customer/{customerId}/vehicle/{vehicleClassId}/pickup/{pickupLocationId}/dropoff/{dropoffLocationId}/period/{rentalPeriod}/insurance/{insuranceOption}/claims24/{claims24}",
			"/rate/customer/{customerId}/vehicle/{vehicleClassId}/pickup/{pickupLocationId}/dropoff/{dropoffLocationId}/period/{rentalPeriod}/insurance/{insuranceOption}",
			"/rate/customer/{customerId}/vehicle/{vehicleClassId}/pickup/{pickupLocationId}/dropoff/{dropoffLocationId}/period/{rentalPeriod}/insurance/{insuranceOption}/claims24/{claims24}/referredBy/{referredBy}",
			"/rate/customer/{customerId}/vehicle/{vehicleClassId}/pickup/{pickupLocationId}/dropoff/{dropoffLocationId}/period/{rentalPeriod}/insurance/{insuranceOption}/referredBy/{referredBy}",
			"/rate/customer/{customerId}/vehicle/{vehicleClassId}/pickup/{pickupLocationId}/dropoff/{dropoffLocationId}/period/{rentalPeriod}/insurance/{insuranceOption}"
	})
	public Money calculateRentalCharge(@PathVariable Map<String,String> pathVariables) {
		if (null == pathVariables.get("customerId")
		 || null == pathVariables.get("vehicleClassId")
		 || null == pathVariables.get("pickupLocationId")
	     || null == pathVariables.get("dropoffLocationId")) {
			throw new MissingArgumentForRateCalculationException();
		}
		Long customerId = Long.valueOf(pathVariables.get("customerId"));
		Long vehicleClassId = Long.valueOf(pathVariables.get("vehicleClassId"));
		Long pickupLocationId = Long.valueOf(pathVariables.get("pickupLocationId"));
		Long dropoffLocationId = Long.valueOf(pathVariables.get("dropoffLocationId"));
		String tempValue = pathVariables.get("claims24");
		int insuranceClaimsLast24Months = tempValue == null ? 0 : Integer.valueOf(tempValue);
		tempValue = pathVariables.get("claims36");
		int insuranceClaimsLast36Months = tempValue == null ? 0 : Integer.valueOf(tempValue);
		String referredByMemberId = pathVariables.get("referredBy");
		String insuranceOption = pathVariables.get("insurance");
		tempValue = pathVariables.get("passengers");
		int numberOfPassengers = tempValue == null ? 0 : Integer.valueOf(tempValue);
		tempValue = pathVariables.get("estimatedMileage");
		int estimatedMileage = tempValue == null ? 0 : Integer.valueOf(tempValue);
		String rentalPeriod = pathVariables.get("period");
		if (Strings.isEmpty(rentalPeriod)) {
			rentalPeriod = "daily";
		}

		return service.dailyRentalRate(
				customerId,
				vehicleClassId,
				pickupLocationId,
				dropoffLocationId,
				insuranceClaimsLast24Months,
				insuranceClaimsLast36Months,
			    referredByMemberId,
				insuranceOption,
				numberOfPassengers,
				estimatedMileage,
				rentalPeriod);
		}

}