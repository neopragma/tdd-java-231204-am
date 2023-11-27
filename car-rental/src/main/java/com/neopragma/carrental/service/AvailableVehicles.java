package com.neopragma.carrental.service;

import com.neopragma.carrental.service.AvailableVehiclesQueryResult;
import com.neopragma.carrental.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvailableVehicles {

    public AvailableVehicles() {}

    @Autowired
    AvailableVehiclesQueryResult qr;

    private String countryCode;

    public String getCountryCode() {
        return countryCode;
    }

    public String getCityName() {
        return cityName;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getPowerType() {
        return powerType;
    }

    public String getTransmission() {
        return transmission;
    }

    public String getMessage() {
        return message;
    }

    public String getCount() {
        return String.valueOf(count);
    }

    private String cityName;
    private String airportCode;

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setPowerType(String powerType) {
        this.powerType = powerType;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String vehicleType;
    private String powerType;
    private String transmission;
    private String message = null;

    private Integer count = 0;


    // this is fake - initial experimentation
    // need to do a database query at this point
    public AvailableVehiclesQueryResult queryAvailableVehicleCount() {
        AvailableVehiclesQueryResult qr;
        if (Strings.isEmpty(message)) {
            qr = new AvailableVehiclesQueryResult(34, "OK");
            this.message = qr.message();
        } else {
            qr = new AvailableVehiclesQueryResult(0, this.message);
        }
        return qr;
    }


}
