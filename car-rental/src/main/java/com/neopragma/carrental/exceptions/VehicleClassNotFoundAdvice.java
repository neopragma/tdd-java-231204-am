package com.neopragma.carrental.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class VehicleClassNotFoundAdvice {

	@ResponseBody
	@ExceptionHandler(VehicleClassNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String vehicleClassNotFoundHandler(VehicleClassNotFoundException ex) {
		return ex.getMessage();
	}
}
