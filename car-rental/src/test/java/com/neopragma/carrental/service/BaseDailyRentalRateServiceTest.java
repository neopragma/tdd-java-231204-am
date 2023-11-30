package com.neopragma.carrental.service;

import com.neopragma.carrental.model.BaseDailyRentalRate;
import com.neopragma.carrental.model.Customer;
import com.neopragma.carrental.model.LocationAndVehicleClassKey;
import com.neopragma.carrental.persistence.BaseDailyRentalRateRepository;
import com.neopragma.carrental.persistence.CustomerRepository;
import com.neopragma.carrental.service.BaseDailyRentalRateService;
import com.neopragma.carrental.service.CustomerService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BaseDailyRentalRateServiceTest {
    private BaseDailyRentalRate baseDailyRentalRate;

    @Mock
    private BaseDailyRentalRateRepository repository;

    @InjectMocks
    private BaseDailyRentalRateService service;

    @BeforeEach
    public void setup() {
        service = new BaseDailyRentalRateService(repository);
    }

    @Test
    public void it_returns_one_rate_based_on_id() throws Exception {
        when(repository.findById(any())).thenReturn(
                Optional.of(new BaseDailyRentalRate(
                        1L, 1L,
                        Money.of(28, "USD")
                )));
        assertEquals(Money.of(28, "USD"),
                service.findById(null)
                        .getBaseDailyRate());
    }

}
