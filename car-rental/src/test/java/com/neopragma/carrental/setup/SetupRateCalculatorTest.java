package com.neopragma.carrental.setup;

import com.neopragma.carrental.RateCalculator;
import com.neopragma.carrental.util.Config;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

public class SetupRateCalculatorTest {

    private RateCalculator rateCalc;

    @BeforeAll
    public static void oneTimeInitialization() {
        Config.load();
    }
    @BeforeEach
    public void beforeEachTestCase() {
        rateCalc = new RateCalculator();
    }

    @ParameterizedTest
    @MethodSource("provideValuesForDailyRentalRateCalculation")
    public void it_calculates_the_daily_rental_rate(
            Money expectedDailyRate,
            String failureMessage,
            String pickupCountry,
            String pickupState,
            String pickupCity,
            String pickupIATA,
            String dropoffCountry,
            String dropoffState,
            String dropoffCity,
            String dropoffIATA,
            String vehicleType,
            String transmissionType,
            String powerType,
            LocalDate customerDateOfBirth,
            boolean smoker,
            String drivingRecord,
            int insuranceClaimsLast24Months,
            int insuranceClaimsLast36Months,
            String uraniumPlusMemberId,
            String referredByMemberId,
            LocalDate lastReferral,
            String customerStanding,
            String insuranceOption,
            int numberOfPassengers,
            int estimatedMileage,
            String rentalPeriod) {
        assertEquals(expectedDailyRate,
                rateCalc.calculateDailyRate(
                        pickupCountry, pickupState, pickupCity, pickupIATA,
                        dropoffCountry, dropoffState, dropoffCity, dropoffIATA,
                        vehicleType, transmissionType, powerType,
                        customerDateOfBirth, smoker, drivingRecord,
                        insuranceClaimsLast24Months, insuranceClaimsLast36Months,
                        uraniumPlusMemberId, referredByMemberId, lastReferral,
                        customerStanding, insuranceOption, numberOfPassengers,
                        estimatedMileage, rentalPeriod),
                    failureMessage
        );
    }

    private static Stream<Arguments> provideValuesForDailyRentalRateCalculation() {
        return Stream.of(
                Arguments.of(Money.of(75.00, "USD"),
                        "US generic with no special considerations",
                        "US", "NE", "Omaha", null, "US", "NE", "Omaha", null,
                        "pickup truck", "automatic", "gasoline",
                        LocalDate.of(1994, 07, 04), false, "average", 0, 0,
                        null, null, null, "good", null, 0, 0, anyString()),

                Arguments.of(Money.of(0.00, "USD"),
                        "US generic - city car is not available",
                        "US", "NE", "Omaha", null, "US", "NE", "Omaha", null,
                        "city car", "automatic", "gasoline",
                        LocalDate.of(1994, 07, 04), false, "average", 0, 0,
                        null, null, null, "good", null, 0, 0, anyString()),

                Arguments.of(Money.of(65.00, "USD"),
                        "California - Los Angeles, LAX airport pickup, no special considerations",
                        "US", "CA", "Los Angeles", "LAX", "US", "CA", "Los Angeles", "LAX",
                        "standard sedan", "automatic", "gasoline",
                        LocalDate.of(1994, 07, 04), false, "good", 0, 0,
                        null, null, null, "good", null, 0, 0, anyString()),

                Arguments.of(Money.of(65.00, "USD"),
                        "California - Los Angeles, not airport pickup, no special considerations",
                        "US", "CA", "Los Angeles", null, "US", "CA", "Los Angeles", null,
                        "standard sedan", "automatic", "gasoline",
                        LocalDate.of(1994, 07, 04), false, "good", 0, 0,
                        null, null, null, "good", null, 0, 0, anyString()),

                Arguments.of(Money.of(65.00, "USD"),
                        "California - San Francisco, SFO airport pickup, no special considerations",
                        "US", "CA", "San Francisco", "SFO", "US", "CA", "San Francisco", "SFO",
                        "standard sedan", "automatic", "gasoline",
                        LocalDate.of(1994, 07, 04), false, "average", 0, 0,
                        null, null, null, "good", null, 0, 0, anyString()),

                Arguments.of(Money.of(65.00, "USD"),
                        "California - San Francisco, not airport pickup, no special considerations",
                        "US", "CA", "San Francisco", null, "US", "CA", "San Francisco", null,
                        "standard sedan", "automatic", "gasoline",
                        LocalDate.of(1994, 07, 04), false, "average", 0, 0,
                        null, null, null, "good", null, 0, 0, anyString()),

                Arguments.of(Money.of(115.00, "USD"),
                        "US generic, customer under 25 years of age",
                        "US", "NE", "Omaha", null, "US", "NE", "Omaha", null,
                        "pickup truck", "automatic", "gasoline",
                        LocalDate.of(2009, 07, 04), false, "average", 0, 0,
                        null, null, null, "good", null, 0, 0, anyString()),

                Arguments.of(Money.of(72.75, "USD"),
                        "US generic, good driving record",
                        "US", "NE", "Omaha", null, "US", "NE", "Omaha", null,
                        "pickup truck", "automatic", "gasoline",
                        LocalDate.of(1994, 07, 04), false, "good", 0, 0,
                        null, null, null, "good", null, 0, 0, anyString()),

                Arguments.of(Money.of(60.00, "USD"),
                        "US Newark, average driving record",
                        "US", "NJ", "Newark", null, "US", "NJ", "Newark", null,
                        "standard sedan", "automatic", "gasoline",
                        LocalDate.of(1994, 07, 04), false, "average", 0, 0,
                        null, null, null, "good", null, 0, 0, anyString()),

                Arguments.of(Money.of(70.00, "USD"),
                        "US Newark, bad driving record",
                        "US", "NJ", "Newark", null, "US", "NJ", "Newark", null,
                        "standard sedan", "automatic", "gasoline",
                        LocalDate.of(1994, 07, 04), false, "bad", 0, 0,
                        null, null, null, "good", null, 0, 0, anyString()),

                Arguments.of(Money.of(70.00, "USD"),
                        "US Newark, bad driving record, 3 claims last 36 months",
                        "US", "NJ", "Newark", "EWR", "US", "NJ", "Newark", "EWR",
                        "standard sedan", "automatic", "gasoline",
                        LocalDate.of(1994, 07, 04), false, "bad", 0, 0,
                        null, null, null, "good", null, 0, 0, anyString()),

                Arguments.of(Money.of(71.00, "USD"),
                        "US Newark, off-airport pickup, good driving record, collision insurance for self, no special considerations",
                        "US", "NJ", "Newark", null, "US", "NJ", "Newark", null,
                        "standard sedan", "automatic", "gasoline",
                        LocalDate.of(1994, 07, 04), false, "good", 0, 0,
                        null, null, null, "good", "collision (self)", 0, 0, anyString()),

                Arguments.of(Money.of(86.00, "USD"),
                        "US Newark, off-airport pickup, good driving record, injury insurance for self + 3 passengers, no special considerations",
                        "US", "NJ", "Newark", null, "US", "NJ", "Newark", null,
                        "standard sedan", "automatic", "gasoline",
                        LocalDate.of(1994, 07, 04), false, "good", 0, 0,
                        null, null, null, "good", "injury (self + passengers)", 3, 0, anyString()),

                Arguments.of(Money.of(78.00, "USD"),
                        "US Newark, off-airport pickup, good driving record, collision insurance for both parties, no special considerations",
                        "US", "NJ", "Newark", null, "US", "NJ", "Newark", null,
                        "standard sedan", "automatic", "gasoline",
                        LocalDate.of(1994, 07, 04), false, "good", 0, 0,
                        null, null, null, "good", "collision (both parties)", 0, 0, anyString()),

                Arguments.of(Money.of(102.00, "USD"),
                        "US Newark, off-airport pickup, good driving record, injury insurance for both parties, no special considerations",
                        "US", "NJ", "Newark", null, "US", "NJ", "Newark", null,
                        "standard sedan", "automatic", "gasoline",
                        LocalDate.of(1994, 07, 04), false, "good", 0, 0,
                        null, null, null, "good", "injury (both parties)", 0, 0, anyString()),

                Arguments.of(Money.of(180.50, "USD"),
                        "US Newark, off-airport pickup, good driving record, full coverage for self + passengers, no special considerations",
                        "US", "NJ", "Newark", null, "US", "NJ", "Newark", null,
                        "suv", "automatic", "gasoline",
                        LocalDate.of(1994, 07, 04), false, "good", 0, 0,
                        null, null, null, "good", "full coverage (self + passengers)", 5, 0, anyString()),

                Arguments.of(Money.of(182, "USD"),
                        "US Newark, off-airport pickup, good driving record, full coverage for all parties, no special considerations",
                        "US", "NJ", "Newark", null, "US", "NJ", "Newark", null,
                        "suv", "automatic", "gasoline",
                        LocalDate.of(1994, 07, 04), false, "good", 0, 0,
                        null, null, null, "good", "full coverage (all parties)", 0, 0, anyString()),

                Arguments.of(Money.of(65.00, "USD"),
                        "US Columbus, off-airport pickup, average driving record, collision insurance for self, no special considerations",
                        "US", "OH", "Columbus", null, "US", "OH", "Columbus", null,
                        "standard sedan", "automatic", "gasoline",
                        LocalDate.of(1994, 07, 04), false, "average", 0, 0,
                        null, null, null, "good", "collision (self)", 0, 0, anyString()),

                Arguments.of(Money.of(71.00, "USD"),
                        "US Columbus, off-airport pickup, average driving record, injury insurance for self + 2 passengers, no special considerations",
                        "US", "OH", "Columbus", null, "US", "OH", "Columbus", null,
                        "standard sedan", "automatic", "gasoline",
                        LocalDate.of(1994, 07, 04), false, "average", 0, 0,
                        null, null, null, "good", "injury (self + passengers)", 0, 0, anyString()),

                Arguments.of(Money.of(61.00, "USD"),
                        "US Columbus, hybrid, off-airport pickup, average driving record, injury insurance for self + 2 passengers, no special considerations",
                        "US", "OH", "Columbus", null, "US", "OH", "Columbus", null,
                        "standard sedan", "automatic", "hybrid gasoline electric",
                        LocalDate.of(1994, 07, 04), false, "average", 0, 0,
                        null, null, null, "good", "injury (self + passengers)", 0, 0, anyString()),

                Arguments.of(Money.of(58, "USD"),
                        "US Columbus, hybrid, off-airport pickup, estimated mileage 150",
                        "US", "OH", "Columbus", null, "US", "OH", "Columbus", null,
                        "standard sedan", "automatic", "gasoline",
                        LocalDate.of(1994, 07, 04), false, "average", 0, 0,
                        null, null, null, "good", null, 0, 150, anyString()),

                Arguments.of(Money.of(62, "USD"),
                        "US Columbus, hybrid, off-airport pickup, estimated mileage 150, smoker",
                        "US", "OH", "Columbus", null, "US", "OH", "Columbus", null,
                        "standard sedan", "automatic", "gasoline",
                        LocalDate.of(1994, 07, 04), true, "average", 0, 0,
                        null, null, null, "good", null, 0, 150, anyString()),

                Arguments.of(Money.of(74, "USD"),
                        "Canada, Vancouver, hybrid, off-airport pickup, estimated mileage 100, smoker",
                        "CA", "BC", "Vancouver", null, anyString(), anyString(), anyString(), null,
                        "standard sedan", "automatic", "gasoline",
                        LocalDate.of(1994, 07, 04), true, "average", 0, 0,
                        null, null, null, "good", null, 0, 300, anyString()),

                Arguments.of(Money.of(80, "USD"),
                        "Canada, Toronto, Pearson Airport pickup, no special considerations",
                        "CA", "ON", "Toronto", "YYZ", anyString(), anyString(), anyString(), null,
                        "suv", "automatic", "gasoline",
                        LocalDate.of(1994, 07, 04), false, "average", 0, 0,
                        null, null, null, "good", null, 0, 0, anyString()),

                Arguments.of(Money.of(75, "USD"),
                        "Spain, Madrid, Barajas Airport pickup, manual, gasoline, no other considerations",
                        "ES", null, "Madrid", "MAD", anyString(), anyString(), anyString(), anyString(),
                        "standard sedan", "manual", "gasoline",
                        LocalDate.of(1990, 07, 04), false, anyString(), 0, 0,
                        null, null, null, "good", null, 0, 0, anyString()),

                Arguments.of(Money.of(78, "USD"),
                        "Spain, Madrid, Barajas Airport pickup, manual, gasoline, estimated 100 miles",
                        "ES", null, "Madrid", "MAD", anyString(), anyString(), anyString(), anyString(),
                        "standard sedan", "manual", "gasoline",
                        LocalDate.of(1990, 07, 04), false, anyString(), 0, 0,
                        null, null, null, "good", null, 0, 100, anyString()),

                Arguments.of(Money.of(100, "USD"),
                        "Spain, Madrid, Barajas Airport pickup, automatic, gasoline, estimated 100 miles",
                        "ES", null, "Madrid", "MAD", anyString(), anyString(), anyString(), anyString(),
                        "suv", "automatic", "gasoline",
                        LocalDate.of(1990, 07, 04), false, anyString(), 0, 0,
                        null, null, null, "good", null, 0, 100, anyString()),

                Arguments.of(Money.of(62, "USD"),
                        "Spain, Madrid, off-Airport pickup, manual, electric, estimated 100 miles",
                        "ES", null, "Madrid", null, anyString(), anyString(), anyString(), null,
                        "standard sedan", anyString(), "electric",
                        LocalDate.of(1990, 07, 04), false, anyString(), 0, 0,
                        null, null, null, "good", null, 0, 100, anyString()),

                Arguments.of(Money.of(81, "USD"),
                        "France, Paris, Charles de Gaulle Airport pickup, manual, gasoline, estimated 100 miles",
                        anyString(), null, anyString(), anyString(), "FR", null, "Paris", "CDG",
                        "standard sedan", "automatic", "gasoline",
                        LocalDate.of(1990, 07, 04), false, anyString(), 0, 0,
                        null, null, null, "good", null, 0, 100, anyString()),

                Arguments.of(Money.of(72, "USD"),
                        "France, Paris, monthly rental, Charles de Gaulle Airport pickup, manual, gasoline, estimated 100 miles",
                        anyString(), null, anyString(), anyString(), "FR", null, "Paris", "CDG",
                        "standard sedan", "automatic", "gasoline",
                        LocalDate.of(1990, 07, 04), false, anyString(), 0, 0,
                        null, null, null, "good", null, 0, 100, "monthly"),

                Arguments.of(Money.of(64, "USD"),
                        "France, Paris, weekly rental, off-airport pickup, electric, estimated 100 miles",
                        anyString(), null, anyString(), anyString(), "FR", null, "Paris", "CDG",
                        "standard sedan", anyString(), "electric",
                        LocalDate.of(1990, 07, 04), false, anyString(), 0, 0,
                        null, null, null, "good", null, 0, 100, "weekly")
        );
    }

    @ParameterizedTest
    @MethodSource("provideValuesForAirportFeeCalculation")
    public void it_calculates_the_airport_fee(
            Money expectedAirportFee,
            String failureMessage,
            String pickupCountry,
            String pickupState,
            String pickupCity,
            String pickupIATA,
            String dropoffCountry,
            String dropoffState,
            String dropoffCity,
            String dropoffIATA) {
        assertEquals(expectedAirportFee,
                rateCalc.calculateAirportFee(
                        pickupCountry, pickupState, pickupCity, pickupIATA,
                        dropoffCountry, dropoffState, dropoffCity, dropoffIATA),
                failureMessage
        );
    }

    private static Stream<Arguments> provideValuesForAirportFeeCalculation() {
        return Stream.of(
                Arguments.of(Money.of(45, "USD"),
                        "Los Angeles LAX pickup",
                        "US", "CA", "Los Angeles", "LAX", anyString(), anyString(), anyString(), anyString()),
                Arguments.of(Money.of(0, "USD"),
                        "Los Angeles off-airport pickup",
                        "US", "CA", "Los Angeles", null, anyString(), anyString(), anyString(), anyString()),
                Arguments.of(Money.of(44, "USD"),
                        "San Francisco SFO pickup",
                        "US", "CA", "San Francisco", "SFO", anyString(), anyString(), anyString(), anyString()),
                Arguments.of(Money.of(0, "USD"),
                        "San Francisco off-airport pickup",
                        "US", "CA", "San Francisco", null, anyString(), anyString(), anyString(), anyString()),
                Arguments.of(Money.of(29, "USD"),
                        "Miami International airport pickup",
                        "US", "FL", "Miami", "MIA", anyString(), anyString(), anyString(), anyString()),
                Arguments.of(Money.of(0, "USD"),
                        "Miami International off-airport pickup",
                        "US", "FL", "Miami", null, anyString(), anyString(), anyString(), anyString()),
                Arguments.of(Money.of(30, "USD"),
                        "Hartsfield-Jackson ATL airport pickup",
                        "US", "GA", "Atlanta", "ATL", anyString(), anyString(), anyString(), anyString()),
                Arguments.of(Money.of(0, "USD"),
                        "Atlanta off-airport pickup",
                        "US", "GA", "Atlanta", null, anyString(), anyString(), anyString(), anyString()),
                Arguments.of(Money.of(27, "USD"),
                        "Charlotte airport pickup",
                        "US", "SC", "Charlotte", "CLT", anyString(), anyString(), anyString(), anyString()),
                Arguments.of(Money.of(0, "USD"),
                        "Charlotte off-airport pickup",
                        "US", "SC", "Charlotte", null, anyString(), anyString(), anyString(), anyString()),
                Arguments.of(Money.of(34, "USD"),
                        "Dallas/Ft. Worth",
                        "US", "TX", "Ft. Worth", "DFW", anyString(), anyString(), anyString(), anyString()),
                Arguments.of(Money.of(0, "USD"),
                        "Dallas off-airport pickup",
                        "US", "TX", "Dallas", null, anyString(), anyString(), anyString(), anyString()),
                Arguments.of(Money.of(32, "USD"),
                        "Houston International airport pickup",
                        "US", "TX", "Houston", "IAH", anyString(), anyString(), anyString(), anyString()),
                Arguments.of(Money.of(0, "USD"),
                        "Houston off-airport pickup",
                        "US", "TX", "Houston", null, anyString(), anyString(), anyString(), anyString()),
                Arguments.of(Money.of(48.5, "USD"),
                        "Newark Liberty airport pickup",
                        "US", "NJ", "Newark", "EWR", anyString(), anyString(), anyString(), anyString()),
                Arguments.of(Money.of(0, "USD"),
                        "Newark off-airport pickup",
                        "US", "NJ", "Newark", null, anyString(), anyString(), anyString(), anyString()),
                Arguments.of(Money.of(50, "USD"),
                        "JFK Airport pickup",
                        "US", "NY", "NYC", "JFK", anyString(), anyString(), anyString(), anyString()),
                Arguments.of(Money.of(0, "USD"),
                        "New York City off-airport pickup",
                        "US", "NY", "NYC", null, anyString(), anyString(), anyString(), anyString()),
                Arguments.of(Money.of(40, "USD"),
                        "Frankfurt airport dropoff",
                        anyString(), anyString(), anyString(), anyString(), "DE", null, "Frankfurt", "FRA"),
                Arguments.of(Money.of(0, "USD"),
                        "Frankfurt off-airport dropoff",
                        anyString(), anyString(), anyString(), anyString(), "DE", null, "Frankfurt", null),
                Arguments.of(Money.of(35, "USD"),
                        "Munich airport dropoff",
                        anyString(), anyString(), anyString(), anyString(), "DE", null, "Munich", "MUC"),
                Arguments.of(Money.of(0, "USD"),
                        "Munich off-airport dropoff",
                        anyString(), anyString(), anyString(), anyString(), "DE", null, "Munich", null),
                Arguments.of(Money.of(42, "USD"),
                        "Charles deGaulle airport dropoff",
                        anyString(), anyString(), anyString(), anyString(), "FR", null, "Paris", "CDG"),
                Arguments.of(Money.of(0, "USD"),
                        "Paris off-airport dropoff",
                        anyString(), anyString(), anyString(), anyString(), "FR", null, "Paris", null),
                Arguments.of(Money.of(31, "USD"),
                        "Madrid airport pickup",
                        "ES", null, "Madrid", "MAD", anyString(), anyString(), anyString(), anyString()),
                Arguments.of(Money.of(0, "USD"),
                        "Madrid off-airport pickup",
                        "ES", null, "Madrid", null, anyString(), anyString(), anyString(), anyString()),
                Arguments.of(Money.of(47, "USD"),
                        "Barcelona airport pickup",
                        "ES", null, "Barcelona", "BCN", anyString(), anyString(), anyString(), anyString()),
                Arguments.of(Money.of(0, "USD"),
                        "Barcelona off-airport pickup",
                        "ES", null, "Barcelona", null, anyString(), anyString(), anyString(), anyString()));
    }
}
