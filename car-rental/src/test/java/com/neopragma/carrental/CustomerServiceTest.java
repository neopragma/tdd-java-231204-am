package com.neopragma.carrental;

import com.neopragma.carrental.persistence.CustomerRepository;
import com.neopragma.carrental.model.Customer;
import com.neopragma.carrental.service.CustomerService;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    private Customer customer;

    @Mock
    private CustomerRepository repository;

    @InjectMocks
    private CustomerService service;

    @BeforeEach
    public void setup() {
        service = new CustomerService(repository);
    }

    @Test
    public void it_returns_one_customer_based_on_id() throws Exception {
        when(repository.findById(anyLong())).thenReturn(
                Optional.of(new Customer("name",
                        LocalDate.of(2000, 3, 4),
                        false,
                        "12345",
                        LocalDate.of(2024, 1, 2)))
        );
        assertEquals("name", service.findById(1L).getName());
        assertEquals("12345", service.findById(1L).getUraniumPlusMemberNumber());
    }
}
