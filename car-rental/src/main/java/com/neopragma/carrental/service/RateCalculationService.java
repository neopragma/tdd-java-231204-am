package com.neopragma.carrental.service;

import com.neopragma.carrental.model.*;
import com.neopragma.carrental.util.RateCalculator;
import org.javamoney.moneta.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RateCalculationService {
    public RateCalculationService(
            RateCalculator calc,
            CustomerService customerService,
            VehicleClassService vehicleClassService,
            LocationService locationService,
            BaseDailyRentalRateService baseDailyRentalRateService) {
        this.calc = calc;
        this.customerService = customerService;
        this.vehicleClassService = vehicleClassService;
        this.locationService = locationService;
        this.baseDailyRentalRateService = baseDailyRentalRateService;
    }
    public RateCalculationService() {}
    @Autowired
    private RateCalculator calc;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private VehicleClassService vehicleClassService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private BaseDailyRentalRateService baseDailyRentalRateService;

    public Money dailyRentalRate(
            Long customerId,
            Long vehicleClassId,
            Long pickupLocationId,
            Long dropoffLocationId,
            int insuranceClaimsLast24Months,
            int insuranceClaimsLast36Months,
            String referredByMemberId,
            String insuranceOption,
            int numberOfPassengers,
            int estimatedMileage,
            String rentalPeriod) {
        Customer customer = customerService.findById(customerId);
        VehicleClass vehicleClass = vehicleClassService.findById(vehicleClassId);
        Location pickupLocation = locationService.findById(pickupLocationId);
        Location dropoffLocation = locationService.findById(dropoffLocationId);
        BaseDailyRentalRate baseDailyRentalRate = baseDailyRentalRateService
                .findById(new LocationAndVehicleClassKey(pickupLocationId, vehicleClassId));
        return calc.calculateDailyRate(
            pickupLocation.getCountry(),
            pickupLocation.getState(),
            pickupLocation.getCity(),
            pickupLocation.getIata(),
            dropoffLocation.getCountry(),
            dropoffLocation.getState(),
            dropoffLocation.getCity(),
            dropoffLocation.getIata(),
            vehicleClass.getName(),
            vehicleClass.getTransmissionType(),
            vehicleClass.getPowerType(),
            customer.getDateOfBirth(),
            customer.isSmoker(),
            customer.getDrivingRecord(),
            insuranceClaimsLast24Months,
            insuranceClaimsLast36Months,
            customer.getUraniumPlusMemberNumber(),
            referredByMemberId,
            customer.getLastReferral(),
            customer.getStanding(),
            insuranceOption,
            numberOfPassengers,
            estimatedMileage,
            rentalPeriod);
    }
}
