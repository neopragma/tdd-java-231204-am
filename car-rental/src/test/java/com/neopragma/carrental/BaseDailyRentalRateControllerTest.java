package com.neopragma.carrental;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.stream.Stream;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class BaseDailyRentalRateControllerTest {

    @Autowired
    WebApplicationContext context;
    private MockMvc mvc;

    @BeforeEach
    public void setUp() throws Exception {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @ParameterizedTest
    @MethodSource("provideValuesForFindOneTests")
    public void it_returns_one_base_daily_rental_rate_based_on_id(
            String locationIdAsString,
            String vehicleClassIdAsString,
            Number expectedRateAmount,
            String expectedRateCurrencyCode
    ) throws Exception {
        mvc.perform(MockMvcRequestBuilders
            .get("/baseDailyRentalRate/location/"
                    + locationIdAsString
                    + "/vehicleClass/" + vehicleClassIdAsString)
                .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers
                    .jsonPath("$.baseDailyRate.number").value(expectedRateAmount))
            .andExpect(MockMvcResultMatchers
                    .jsonPath("$.baseDailyRate.currency.currencyCode").value(expectedRateCurrencyCode));
    }
    private static Stream<Arguments> provideValuesForFindOneTests() {
        return Stream.of(
                // Location 1 is Los Angeles
                Arguments.of("1", "1", 28, "USD"),
                Arguments.of("1", "2", 52, "USD"),
                Arguments.of("1", "3", 65, "USD"),
                Arguments.of("1", "4", 105, "USD"),
                Arguments.of("1", "5", 95, "USD"),
                Arguments.of("1", "6", 95, "USD")
        );
    }

}
