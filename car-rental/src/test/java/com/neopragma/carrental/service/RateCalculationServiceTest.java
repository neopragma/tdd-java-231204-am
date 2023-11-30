package com.neopragma.carrental.service;

import com.neopragma.carrental.model.Customer;
import com.neopragma.carrental.model.Location;
import com.neopragma.carrental.model.VehicleClass;
import com.neopragma.carrental.persistence.CustomerRepository;
import com.neopragma.carrental.util.RateCalculator;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RateCalculationServiceTest {
    private RateCalculator calc;
    @InjectMocks
    private RateCalculationService service;
    @Mock
    private CustomerService customerService;
    @Mock
    private VehicleClassService vehicleClassService;
    @Mock
    private LocationService locationService;
    @Mock
    private BaseDailyRentalRateService baseDailyRentalRateService;
    @Mock
    private Location pickupLocation;
    @Mock
    private Location dropoffLocation;
    @Mock
    private VehicleClass vehicleClass;
    @Mock
    private Customer customer;

    @BeforeEach
    public void setup() {
        calc = new RateCalculator();
        service = new RateCalculationService(
                calc,
                customerService,
                vehicleClassService,
                locationService,
                baseDailyRentalRateService);
    }

    @Test
    public void it_calculates_the_daily_rental_rate_for_Los_Angeles() {
        when(customer.getDateOfBirth())
                .thenReturn(LocalDate.of(1994, 7, 15));
        when(customer.getDrivingRecord())
                .thenReturn("good");
        when(customerService.findById(1L))
                .thenReturn(customer);
        when(vehicleClass.getName())
                .thenReturn("standard sedan");
        when(vehicleClass.getPowerType())
                .thenReturn("gasoline");
        when(vehicleClass.getTransmissionType())
                .thenReturn("automatic");
        when(vehicleClassService.findById(1L))
                .thenReturn(vehicleClass);
        when(pickupLocation.getCountry())
                .thenReturn("US");
        when(pickupLocation.getState())
                .thenReturn("CA");
        when(pickupLocation.getCity())
                .thenReturn("Los Angeles");
        when(locationService.findById(1L))
                .thenReturn(pickupLocation)
                .thenReturn(dropoffLocation);
        assertEquals(Money.of(65.00, "USD"),
                service.dailyRentalRate(1L,
                        1L,
                        1L,
                        1L,
                        0,
                        0,
                        null,
                        null,
                        0,
                        0,
                        "daily"),
                "California - Los Angeles, LAX airport pickup, no special considerations");
    }
}
