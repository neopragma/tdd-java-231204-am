package com.neopragma.carrental.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CompositeType;

import javax.money.MonetaryAmount;
import javax.persistence.GeneratedValue;
//import javax.persistence.*;
import java.text.MessageFormat;
import java.util.Objects;
import io.hypersistence.utils.hibernate.type.money.MonetaryAmountType;

@Entity
@Table(name = "airport")
public class Airport {

// Note: @GeneratedValue does not seem to have an effect
	private @Id @GeneratedValue
	Long id;

	@Column(name = "iata", unique = true)
	private String iata;
	private String name;
	@AttributeOverrides({
			@AttributeOverride(
					name = "airportFeeAmount",
					column = @Column(name = "airport_fee_amount")
			),
			@AttributeOverride(
					name = "airportFeeCurrency",
					column = @Column(name = "airport_fee_currency")
			)
	})
	@CompositeType(MonetaryAmountType.class)
	private MonetaryAmount airportFee;

	public Airport(String name, String iata, MonetaryAmount airportFee) {
		this.name = name;
		this.iata = iata;
		this.airportFee = airportFee;
	}
	public Airport(Long id, String name, String iata, MonetaryAmount airportFee) {
		this.id = id;
		this.name = name;
		this.iata = iata;
		this.airportFee = airportFee;
	}

	public Airport() {}

	public Long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getIata() { return this.iata; }

	public MonetaryAmount getAirportFee() {
		return airportFee;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setIata(String iata) { this.iata = iata; }

	public void setAirportFee(MonetaryAmount airportFee) {
		this.airportFee = airportFee;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Airport airport))
			return false;
		return Objects.equals(this.id, airport.id) && Objects.equals(this.name, airport.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.name);
	}

	@Override
	public String toString() {
		return MessageFormat.format("Airport: '{'id={0}, name=''{1}'''}'", this.id, this.name);
	}
}
