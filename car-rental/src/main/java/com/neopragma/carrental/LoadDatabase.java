package com.neopragma.carrental;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.neopragma.carrental.model.*;
import com.neopragma.carrental.persistence.*;
import org.javamoney.moneta.Money;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Optional;

@Configuration
class LoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	CommandLineRunner loadVehicleClasses(VehicleClassRepository repository) {
		return args -> {
			log.info("Preloading " + repository.save(new VehicleClass("city car")));
			log.info("Preloading " + repository.save(new VehicleClass("economy")));
			log.info("Preloading " + repository.save(new VehicleClass("standard sedan")));
			log.info("Preloading " + repository.save(new VehicleClass("luxury sedan")));
			log.info("Preloading " + repository.save(new VehicleClass("suv")));
			log.info("Preloading " + repository.save(new VehicleClass("pickup truck")));
		};
	}
	@Bean
	CommandLineRunner loadCustomers(CustomerRepository repository) {
		return args -> {
			log.info("Preloading " + repository.save(
					new Customer("Walt Whitman",
							LocalDate.of(1995, 10, 15),
							false, null, null)));
			log.info("Preloading " + repository.save(
					new Customer("Yuri Raniumsky",
							LocalDate.of(1999, 4, 1),
							true,
							"92-238-03",
							LocalDate.of(2021, 8, 17))));
		};
	}

	@Bean
	CommandLineRunner loadAirports(AirportRepository repository) {
		Airport airport;
		return args -> {
			log.info("Preloading " + repository.save(
					new Airport(1L,
							"Hartsfield-Jackson Atlanta International Airport",
							"ATL",
							Money.of(30, "USD"))));
			log.info("Preloading " + repository.save(
					new Airport(2L,
							"Aeropuerto Josep Tarradellas Barcelona-El Prat",
							"BCN", Money.of(47, "USD"))));
			log.info("Preloading " + repository.save(
					new Airport(3L,
							"Aéroport de Paris-Charles-de-Gaulle",
							"CDG", Money.of(42, "USD"))));
			log.info("Preloading " + repository.save(
					new Airport(4L,
							"Charlotte Douglas International Airport",
							"CLT", Money.of(27, "USD"))));
			log.info("Preloading " + repository.save(
					new Airport(5L,
							"Denver International Airport",
							"DEN", Money.of(28, "USD"))));
			log.info("Preloading " + repository.save(
					new Airport(6L,
							"Dallas Fort Worth International Airport",
							"DFW", Money.of(34, "USD"))));
			log.info("Preloading " + repository.save(
					new Airport(7L,
							"Newark Liberty International Airport",
							"EWR", Money.of(48.50, "USD"))));
			log.info("Preloading " + repository.save(
					new Airport(8L,
							"Flughafen Frankfurt Main",
							"FRA", Money.of(40, "USD"))));
			log.info("Preloading " + repository.save(
					new Airport(9L,
							"George Bush Intercontinental Airport",
							"IAH", Money.of(32, "USD"))));
			log.info("Preloading " + repository.save(
					new Airport(10L,
							"John F. Kennedy International Airport",
							"JFK", Money.of(50, "USD"))));
			log.info("Preloading " + repository.save(
					new Airport(11L,
							"Los Angeles International Airport",
							"LAX", Money.of(45, "USD"))));
			log.info("Preloading " + repository.save(
					new Airport(12L,
							"Aeropuerto Adolfo Suárez Madrid-Barajas",
							"MAD", Money.of(31, "USD"))));
			log.info("Preloading " + repository.save(
					new Airport(13L,
							"Miami International Airport",
							"MIA", Money.of(29, "USD"))));
			log.info("Preloading " + repository.save(
					new Airport(14L,
							"Flughafen München",
							"MUC", Money.of(35, "USD"))));
			log.info("Preloading " + repository.save(
					new Airport(15L,
							"San Francisco International Airport",
							"SFO", Money.of(44, "USD"))));
			log.info("Preloading " + repository.save(
					new Airport(16L,
							"Toronto Pearson International Airport",
							"YYZ", Money.of(31.75, "USD"))));
		};
	}

	@Bean
	CommandLineRunner loadLocations(LocationRepository repository) {
		return args -> {
			log.info("Preloading " + repository.save(new Location(
					"en","US","CA","Los Angeles","LAX")));
		};
	}
	@Bean
	CommandLineRunner loadBaseDailyRentalRates(BaseDailyRentalRateRepository repository) {
		return args -> {
			// Los Angeles, CA, US
			log.info("Preloading " + repository.save(new BaseDailyRentalRate(
					1L, 1L, Money.of(28, "USD"))));
			log.info("Preloading " + repository.save(new BaseDailyRentalRate(
					1L, 2L, Money.of(52, "USD"))));
			log.info("Preloading " + repository.save(new BaseDailyRentalRate(
					1L, 3L, Money.of(65, "USD"))));
			log.info("Preloading " + repository.save(new BaseDailyRentalRate(
					1L, 4L, Money.of(105, "USD"))));
			log.info("Preloading " + repository.save(new BaseDailyRentalRate(
					1L, 5L, Money.of(95, "USD"))));
			log.info("Preloading " + repository.save(new BaseDailyRentalRate(
					1L, 6L, Money.of(95, "USD"))));
		};
	}
}
