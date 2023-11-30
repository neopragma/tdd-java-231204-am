package com.neopragma.carrental.util;

import com.neopragma.carrental.util.Config;
import org.javamoney.moneta.Money;
import org.springframework.stereotype.Component;

import javax.money.MonetaryAmount;
import java.time.LocalDate;
import java.time.Period;

@Component
public class RateCalculator {

    public Money calculateDailyRate(
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

        Money dailyRate = Money.of(0.00, "USD");
        if (smoker) {
            if (pickupCountry.equals("US") || pickupCountry.equals("CA") || dropoffCountry.equals("DE")) {
                dailyRate = dailyRate.add(Money.of(4, "USD"));
            }
        }
        if (pickupCountry.equals("US")) {
            if (!pickupState.equals("CA") && !pickupState.equals("NY") && !pickupState.equals("NJ")) {
                if (vehicleType.equals("city car")) {
                    return dailyRate;
                } else {
                    switch (vehicleType) {
                        case "economy":
                            dailyRate = dailyRate.add(Money.of(45.00, "USD"));
                            break;
                        case "standard sedan":
                            dailyRate = dailyRate.add(Money.of(55.00, "USD"));
                            break;
                        default:
                            dailyRate = dailyRate.add(Money.of(75.00, "USD"));
                    }
                }
            } else if (pickupState.equals("CA")) {
                if (!pickupCity.equals("San Francisco") && !pickupCity.equals("Los Angeles") && !pickupCity.equals("Sacramento")) {
                    if (vehicleType.equals("city car")) {
                        return dailyRate;
                    } else {
                        dailyRate = dailyRate.add(Money.of(28.00, "USD"));
                    }
                } else {
                    switch (vehicleType) {
                        case "economy":
                            dailyRate = dailyRate.add(Money.of(52.00, "USD"));
                            break;
                        case "standard sedan":
                            dailyRate = dailyRate.add(Money.of(65.00, "USD"));
                            break;
                        case "luxury sedan":
                            dailyRate = dailyRate.add(Money.of(105.00, "USD"));
                            break;
                        default:
                            dailyRate = dailyRate.add(Money.of(95.00, "USD"));
                    }
                }
            } else if (vehicleType.equals("city car")) {
                if (pickupState.equals("NJ") || pickupState.equals("NY")) {
                    if (!pickupCity.equals("NYC") && !pickupCity.equals("Newark")) {
                        return dailyRate;
                    } else {
                        dailyRate = dailyRate.add(Money.of(35.00, "USD"));
                    }
                }
            } else {
                if (pickupState.equals("NY") || pickupState.equals("NJ")) {
                    switch (vehicleType) {
                        case "economy":
                            dailyRate = dailyRate.add(Money.of(45.00, "USD"));
                            break;
                        case "standard sedan":
                            dailyRate = dailyRate.add(Money.of(58.00, "USD"));
                            break;
                        case "luxury sedan":
                            dailyRate = dailyRate.add(Money.of(123.00, "USD"));
                            break;
                        case "suv":
                            dailyRate = dailyRate.add(Money.of(130.00, "USD"));
                            break;
                        case "pickup truck":
                            dailyRate = dailyRate.add(Money.of(81.00, "USD"));
                    }
                    if (pickupState.equals("NY")) {
                        if (!vehicleType.equals("pickup truck")) {
                            dailyRate = dailyRate.add(Money.of(10.00, "USD"));
                        }
                    }
                }
            }
            if (pickupCity.equals("NYC") || pickupCity.equals("Newark") || pickupCity.equals("Atlanta")
                || pickupCity.equals("Dallas") || pickupCity.equals("Ft. Worth") || pickupCity.equals("Houston")
                || pickupCity.equals("DC") || pickupCity.equals("Chicago") || pickupCity.equals("Miami")
                || pickupCity.equals("Seattle") || pickupCity.equals("Philadelphia")) {
                if (insuranceOption != null) {
                    switch (insuranceOption) {
                        case "collision (self)":
                            dailyRate = dailyRate.add(Money.of(13, "USD"));
                            break;
                        case "injury (self + passengers)":
                            dailyRate = dailyRate.add(Money.of(19, "USD"));
                            if (numberOfPassengers > 0) {
                                dailyRate = dailyRate.add(Money.of(numberOfPassengers * 3, "USD"));
                            }
                            break;
                        case "collision (both parties)":
                            dailyRate = dailyRate.add(Money.of(20, "USD"));
                            break;
                        case "injury (both parties)":
                            dailyRate = dailyRate.add(Money.of(44, "USD"));
                            break;
                        case "full coverage (self + passengers)":
                            dailyRate = dailyRate.add(Money.of(28, "USD"));
                            if (numberOfPassengers > 0) {
                                dailyRate = dailyRate.add(Money.of(numberOfPassengers * 4.5, "USD"));
                            }
                            break;
                        case "full coverage (all parties)":
                            dailyRate = dailyRate.add(Money.of(52, "USD"));
                            break;
                        }
                    }
                } else {
                    if (insuranceOption != null) {
                        switch(insuranceOption) {
                            case "collision (self)":
                                dailyRate = dailyRate.add(Money.of(10, "USD"));
                                break;
                            case "injury (self + passengers)":
                                dailyRate = dailyRate.add(Money.of(16, "USD"));
                                if (numberOfPassengers > 0) {
                                    dailyRate = dailyRate.add(Money.of(numberOfPassengers * 3, "USD"));
                                }
                                break;
                            case "collision (both parties)":
                                dailyRate = dailyRate.add(Money.of(18, "USD"));
                                break;
                            case "injury (both parties)":
                                dailyRate = dailyRate.add(Money.of(40, "USD"));
                                break;
                            case "full coverage (self + passengers)":
                                dailyRate = dailyRate.add(Money.of(24, "USD"));
                                if (numberOfPassengers > 0) {
                                    dailyRate = dailyRate.add(Money.of(numberOfPassengers * 3, "USD"));
                                }
                                break;
                            case "full coverage (all parties)":
                                dailyRate = dailyRate.add(Money.of(46, "USD"));
                                break;
                        }
                    }
                }
            if (Period.between(customerDateOfBirth, LocalDate.now()).getYears() < 25) {
                dailyRate = dailyRate.add(Money.of(40.00, "USD"));
            } else if (drivingRecord.equals("good")) {
                if (pickupCity.equals("Chicago") || pickupCity.equals("Dallas") ||
                        pickupCity.equals("Ft. Worth") || pickupCity.equals("Houston") ||
                        pickupCity.equals("Miami") || pickupCity.equals("Philadelphia") ||
                        pickupCity.equals("Seattle")) {
                    dailyRate = dailyRate.subtract(Money.of(2.0, "USD"));
                } else if (!pickupCity.equals("Los Angeles") && !pickupCity.equals("Atlanta") &&
                        !pickupCity.equals("NYC") && !pickupCity.equals("Newark") &&
                        !pickupCity.equals("DC")) {
                    dailyRate = dailyRate.subtract(dailyRate.multiply(0.03));
                }
            } else if (drivingRecord.equals("average")) {
                if (pickupCity.equals("Newark") || pickupCity.equals("NYC") ||
                    pickupCity.equals("Los Angeles") || pickupCity.equals("DC") || pickupCity.equals("Atlanta") ||
                    pickupCity.equals("Dallas") || pickupCity.equals("Ft. Worth")) {
                    dailyRate = dailyRate.add(Money.of(2, "USD"));
                }
            } else if (drivingRecord.equals("bad")) {
                if (pickupCity.equals("Los Angeles") || pickupCity.equals("NYC") ||
                        pickupCity.equals("Newark") || pickupCity.equals("DC") || pickupCity.equals("Atlanta")) {
                    dailyRate = dailyRate.add(Money.of(12, "USD"));
                } else if (pickupCity.equals("Miami") || pickupCity.equals("Houston") ||
                        pickupCity.equals("Chicago") || pickupCity.equals("Seattle") ||
                        pickupCity.equals("Philadelphia")) {
                    dailyRate = dailyRate.add(Money.of(7, "USD"));
                } else {
                    dailyRate = dailyRate.add(Money.of(5, "USD"));
                }
            }
            if (powerType != null) {
                if (powerType.equals("electric") || powerType.startsWith("hybrid ")) {
                    dailyRate = dailyRate.subtract(Money.of(10, "USD"));
                }
            }
            if (estimatedMileage > 0) {
                if (pickupState.equals("CA")) {
                    dailyRate = dailyRate.add(Money.of(0.06, "USD").multiply(estimatedMileage));
                } else if (pickupState.equals("NJ")) {
                    dailyRate = dailyRate.add(Money.of(.08, "USD").multiply(estimatedMileage));
                } else if (pickupState.equals("NY")) {
                    dailyRate = dailyRate.add(Money.of(.09, "USD").multiply(estimatedMileage));
                } else {
                    dailyRate = dailyRate.add(Money.of(.02, "USD").multiply(estimatedMileage));
                }
            }
        } else if (pickupCountry.equals("CA")) {
            double baseRateForVehicleType = 0.0;
            switch (pickupCity) {
                case "Vancouver":
                    switch (vehicleType) {
                        case "city car":
                            baseRateForVehicleType = 35.0;
                            break;
                        case "economy":
                            baseRateForVehicleType = 52.0;
                            break;
                        case "standard sedan":
                            baseRateForVehicleType = 58.0;
                            break;
                        case "luxury sedan":
                            baseRateForVehicleType = 114.0;
                            break;
                        case "suv":
                            baseRateForVehicleType = 82.0;
                            break;
                        case "pickup truck":
                            baseRateForVehicleType = 76.0;
                    }
                    break;
                case "Toronto":
                    switch (vehicleType) {
                        case "city car":
                            baseRateForVehicleType = 35.0;
                            break;
                        case "economy":
                            baseRateForVehicleType = 52.0;
                            break;
                        case "standard sedan":
                            baseRateForVehicleType = 58.0;
                            break;
                        case "luxury sedan":
                            baseRateForVehicleType = 108;
                            break;
                        case "suv":
                            baseRateForVehicleType = 80;
                            break;
                        case "pickup truck":
                            baseRateForVehicleType = 75.0;
                    }
                    break;
                default:
                    switch (vehicleType) {
                        case "city car":
                            return Money.of(-1, "USD");
                        case "economy":
                            baseRateForVehicleType = 48.0;
                            break;
                        case "standard sedan":
                            baseRateForVehicleType = 54.0;
                            break;
                        case "luxury sedan":
                            baseRateForVehicleType = 110.0;
                            break;
                        case "suv":
                            baseRateForVehicleType = 78.0;
                            break;
                        case "pickup truck":
                            baseRateForVehicleType = 70.0;
                    }
            }
            dailyRate = dailyRate.add(Money.of(baseRateForVehicleType, "USD"));
            if (powerType != null) {
                double adjustmentForPowerType = 0.0;
                switch (powerType) {
                    case "diesel":
                    case "electric":
                    case "hybrid gasoline electric":
                        adjustmentForPowerType = 4.0;
                        break;
                    case "hydrogen":
                    case "liquefied petroleum gas":
                        adjustmentForPowerType = 2.0;
                        break;
                    case "hybrid diesel electric":
                        adjustmentForPowerType = 5.0;
                        break;
                }
                dailyRate = dailyRate.add(Money.of(adjustmentForPowerType, "USD"));
            }
            if (Period.between(customerDateOfBirth, LocalDate.now()).getYears() < 25) {
                dailyRate = dailyRate.add(Money.of(30.00, "USD"));
            }
            if (estimatedMileage > 0) {
                dailyRate = dailyRate.add(Money.of(0.04, "USD").multiply(estimatedMileage));
            }
        } else if (pickupCountry.equals("ES")) {
            switch (pickupCity) {
                case "Barcelona":
                case "Madrid":
                    if (vehicleType.equals("city car")) {
                        dailyRate = dailyRate.add(Money.of(35, "USD"));
                    } else if (vehicleType.equals("economy")) {
                        dailyRate = dailyRate.add(Money.of(44, "USD"));
                    } else if (vehicleType.equals("standard sedan")) {
                        dailyRate = dailyRate.add(Money.of(63, "USD"));
                    } else if (vehicleType.equals("luxury sedan")) {
                        dailyRate = dailyRate.add(Money.of(90, "USD"));
                    } else if (vehicleType.equals("suv")) {
                        dailyRate = dailyRate.add(Money.of(85, "USD"));
                    } else if (vehicleType.equals("pickup truck")) {
                        dailyRate = dailyRate.add(Money.of(72, "USD"));
                    }
                    break;
                default:
                    if (vehicleType.equals("city car")) {
                        dailyRate = dailyRate.add(Money.of(32, "USD"));
                    } else if (vehicleType.equals("economy")) {
                        dailyRate = dailyRate.add(Money.of(42, "USD"));
                    } else if (vehicleType.equals("standard sedan")) {
                        dailyRate = dailyRate.add(Money.of(55, "USD"));
                    } else if (vehicleType.equals("luxury sedan")) {
                        dailyRate = dailyRate.add(Money.of(63, "USD"));
                    } else if (vehicleType.equals("suv")) {
                        dailyRate = dailyRate.add(Money.of(70, "USD"));
                    } else if (vehicleType.equals("pickup truck")) {
                        dailyRate = dailyRate.add(Money.of(70, "USD"));
                    }
            }
            if (powerType != null) {
                if (powerType.equals("diesel") || powerType.equals("methane") || powerType.equals("liquefied petroleum gas")) {
                    dailyRate = dailyRate.add(Money.of(2, "USD"));
                } else if (powerType.equals("gasoline")) {
                    dailyRate = dailyRate.add(Money.of(12, "USD"));
                } else if (powerType.equals("electric")) {
                    dailyRate = dailyRate.add(Money.of(-4, "USD"));
                } else if (powerType.equals("hybrid diesel electric")) {
                    dailyRate = dailyRate.add(Money.of(-2, "USD"));
                } else if (powerType.equals("hybrid gasoline electric")) {
                    dailyRate = dailyRate.add(Money.of(-1, "USD"));
                }
            }
            if (transmissionType.equals("automatic")) {
                dailyRate.add(Money.of(6, "USD"));
            }
            if (Period.between(customerDateOfBirth, LocalDate.now()).getYears() < 27) {
                dailyRate = dailyRate.add(Money.of(16, "USD"));
            }
            if (estimatedMileage > 0) {
                if (!vehicleType.contains("electric")) {
                    dailyRate = dailyRate.add(Money.of(0.03, "USD").multiply(estimatedMileage));
                }
            }
        } else if (dropoffCountry.equals("FR")) {
            String defaultCurrencyCodeKey = "default.currency.code";
            if (vehicleType.equals("city car")) {
                dailyRate = dropoffCity.equals("Paris")
                        ? Money.of(35, Config.valueOf(defaultCurrencyCodeKey, "USD"))
                        : Money.of(32, Config.valueOf(defaultCurrencyCodeKey, "USD"));
            } else if (vehicleType.equals("economy")) {
                dailyRate = dropoffCity.equals("Paris")
                        ? Money.of(44, Config.valueOf(defaultCurrencyCodeKey, "USD"))
                        : Money.of(42, Config.valueOf(defaultCurrencyCodeKey, "USD"));
            } else if (vehicleType.equals("standard sedan")) {
                dailyRate = dropoffCity.equals("Paris")
                        ? Money.of(63, Config.valueOf(defaultCurrencyCodeKey, "USD"))
                        : Money.of(55, Config.valueOf(defaultCurrencyCodeKey, "USD"));
            } else if (vehicleType.equals("luxury sedan")) {
                dailyRate = dropoffCity.equals("Paris")
                        ? Money.of(90, Config.valueOf(defaultCurrencyCodeKey, "USD"))
                        : Money.of(63, Config.valueOf(defaultCurrencyCodeKey, "USD"));
            } else if (vehicleType.equals("suv")) {
                dailyRate = dropoffCity.equals("Paris")
                        ? Money.of(85, Config.valueOf(defaultCurrencyCodeKey, "USD"))
                        : Money.of(70, Config.valueOf(defaultCurrencyCodeKey, "USD"));
            } else if (vehicleType.equals("pickup truck")) {
                dailyRate = dropoffCity.equals("Paris")
                        ? Money.of(72, Config.valueOf(defaultCurrencyCodeKey, "USD"))
                        : Money.of(70, Config.valueOf(defaultCurrencyCodeKey, "USD"));
            }
            if (powerType != null) {
                if (powerType.equals("gasoline")) {
                    dailyRate = dailyRate.add(Money.of(5, Config.valueOf(defaultCurrencyCodeKey, "USD")));
                } else if (powerType.equals("methane") || powerType.equals("hydrogen") || powerType.equals("hybrid gasoline electric")) {
                    dailyRate = dailyRate.add(Money.of(2, Config.valueOf(defaultCurrencyCodeKey, "USD")));
                }
            }
            if (transmissionType.equals("automatic")) {
                dailyRate = dailyRate.add(Money.of(7, Config.valueOf(defaultCurrencyCodeKey, "USD")));
            }
            if (Period.between(customerDateOfBirth, LocalDate.now()).getYears() < 25) {
                dailyRate = dailyRate.add(Money.of(20, Config.valueOf(defaultCurrencyCodeKey, "USD")));
            }
            if (estimatedMileage > 0) {
                dailyRate = dailyRate.add(Money.of(0.06, "USD").multiply(estimatedMileage));
            }
        } else if (dropoffCountry.equals("DE")) {
            String currencyCode = Config.valueOf("default.currency.code");
            if (vehicleType.startsWith("city")) {
                dailyRate = dailyRate.add(Money.of(33, currencyCode));
            } else if (vehicleType.startsWith("econ")) {
                dailyRate = dailyRate.add(Money.of(54, currencyCode));
            } else if (vehicleType.startsWith("stan")) {
                dailyRate = dailyRate.add(Money.of(66, currencyCode));
            } else if (vehicleType.startsWith("luxu")) {
                dailyRate = dailyRate.add(Money.of(88, currencyCode));
            } else if (vehicleType.equals("suv")) {
                dailyRate = dailyRate.add(Money.of(85, currencyCode));
            } else if (vehicleType.startsWith("pick")) {
                dailyRate = dailyRate.add(Money.of(72, currencyCode));
            } else {
                return Money.of(-1, currencyCode);
            }
            if (dropoffCity.equals("Frankfurt")) {
                if (vehicleType.startsWith("city")) {
                    dailyRate = dailyRate.add(Money.of(5, currencyCode));
                } else if (vehicleType.startsWith("econ")) {
                    dailyRate = dailyRate.add(Money.of(4, currencyCode));
                } else if (vehicleType.startsWith("stan")) {
                    dailyRate = dailyRate.add(Money.of(2, currencyCode));
                } else if (vehicleType.equals("suv")) {
                    dailyRate = dailyRate.add(Money.of(3, currencyCode));
                } else if (!vehicleType.startsWith("luxu")) {
                    return Money.of(-1, currencyCode);
                }
            }
            if (estimatedMileage > 0) {
                dailyRate = dailyRate.add(Money.of(0.05, "USD").multiply(estimatedMileage));
            }
            if (powerType != null) {
                if (powerType.equals("diesel") || powerType.equals("methane") || powerType.equals("hydrogen")) {
                    dailyRate = dailyRate.add(Money.of(2, currencyCode));
                } else if (powerType.equals("gasoline")) {
                    dailyRate = dailyRate.add(Money.of(8, currencyCode));
                } else if (powerType.equals("electric")) {
                    dailyRate = dailyRate.subtract(Money.of(5, currencyCode));
                } else if (powerType.startsWith("hybrid")) {
                    dailyRate = dailyRate.subtract(Money.of(2, currencyCode));
                }
            }
            if (Period.between(customerDateOfBirth, LocalDate.now()).getYears() < 25) {
                dailyRate = dailyRate.add(Money.of(20.00, currencyCode));
            }
            if (drivingRecord.equals("good")) {
                dailyRate = dailyRate.subtract(Money.of(4, currencyCode));
            } else if (drivingRecord.equals("bad")) {
                dailyRate = dailyRate.add(Money.of(3, currencyCode));
            }
        }
        if (rentalPeriod.equals("weekly")) {
            dailyRate = dailyRate.subtract(Money.of(5, "USD"));
        } else if (rentalPeriod.equals("monthly")) {
            dailyRate = dailyRate.subtract(Money.of(9, "USD"));
        }
        return dailyRate;
    }

    public Money calculateAirportFee(
            String pickupCountry,
            String pickupState,
            String pickupCity,
            String pickupIATA,
            String dropoffCountry,
            String dropoffState,
            String dropoffCity,
            String dropoffIATA) {
        Money airportFee = Money.of(0.00, "USD");
        if (pickupCountry.equals("US")) {
            if (pickupState.equals("CA")) {
                if (pickupIATA != null) {
                    airportFee = airportFee.add(Money.of(44.00, "USD"));
                    if (pickupIATA.equals("LAX")) {
                        airportFee = airportFee.add(Money.of(1, "USD"));
                    }
                } else {
                    return airportFee;
                }
            } else if (pickupState.equals("NJ")) {
                    if (pickupIATA != null) {
                        if (pickupIATA.equals("EWR")) {
                            airportFee = airportFee.add(Money.of(48.5, "USD"));
                        }
                    } else {
                        return airportFee;
                    }
                } else if (pickupState.equals("NY")) {
                    if (pickupIATA != null) {
                        if (pickupIATA.equals("JFK")) {
                            airportFee = airportFee.add(Money.of(50, "USD"));
                        }
                    } else {
                        return airportFee;
                    }
                } else if (pickupIATA != null) {
                    if (pickupState.equals("GA")) {
                        if (pickupIATA.equals("ATL")) {
                            airportFee = airportFee.add(Money.of(30, "USD"));
                        }
                    } else if (pickupState.equals("TX")) {
                        airportFee = airportFee.add(Money.of(32, "USD"));
                        if (pickupIATA.equals("DFW")) {
                            airportFee = airportFee.add(Money.of(2, "USD"));
                        }
                    } else if (pickupState.equals("CO")) {
                        if (pickupIATA.equals("DEN")) {
                            airportFee = airportFee.add(Money.of(28, Config.valueOf("default.currency.code")));
                        }
                    } else if (pickupState.equals("SC")) {
                        if (pickupIATA.equals("CLT")) {
                            airportFee = airportFee.add(Money.of(27, "USD"));
                        }
                    } else if (pickupState.equals("FL")) {
                        if (pickupIATA.equals("MIA")) {
                            airportFee = airportFee.add(Money.of(29.00, "USD"));
                        }
                    }
                } else {
                    return airportFee;
            }
        } else if (dropoffCountry.equals("DE")) {
            if (dropoffIATA == null) {
                return Money.of(0, Config.valueOf("default.currency.code"));
            } else {
                airportFee = Money.of(35, Config.valueOf("default.currency.code"));
                if (dropoffIATA.equals("FRA")) {
                    airportFee = airportFee.add(Money.of(5, Config.valueOf("default.currency.code")));
                }
            }
        } else if (dropoffCountry.equals("FR")) {
            if (dropoffIATA != null && dropoffIATA.equals("CDG")) {
                return Money.of(42, "USD");
            }
        } else if (pickupCountry.equals("ES")) {
            if (pickupIATA == null) {
                ;
            } else {
                if (pickupIATA.equals("MAD")) {
                    return Money.of(31, "USD");
                } else if (pickupIATA.equals("BCN")) {
                    return Money.of(47, "USD");
                }
            }
        }
        return airportFee;
    }

    public Money calculateRentalTax(Money dailyRentalRate, int durationInDays) {
        return Money.of(0, "USD");
    }
}
