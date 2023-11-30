package com.neopragma.carrental.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import javax.persistence.Table;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "customer")
public class Customer {
	public Customer(String name, LocalDate dateOfBirth, boolean smoker, String uraniumPlusMemberNumber, LocalDate uraniumPlusSince) {
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.smoker = smoker;
		this.uraniumPlusMemberNumber = uraniumPlusMemberNumber;
		this.uraniumPlusSince = uraniumPlusSince;
	}
	public Customer() {}

	private @Id
	@GeneratedValue Long id;
	private String name;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate dateOfBirth;
	private boolean smoker;
	private String drivingRecord;
	private String standing;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate lastReferral;
	private String uraniumPlusMemberNumber;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate uraniumPlusSince;



	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public boolean isSmoker() {
		return smoker;
	}
	public void setSmoker(boolean smoker) {
		this.smoker = smoker;
	}
	public String getDrivingRecord() {
		return drivingRecord;
	}
	public void setDrivingRecord(String drivingRecord) {
		this.drivingRecord = drivingRecord;
	}
	public String getStanding() {
		return standing;
	}
	public void setStanding(String standing) {
		this.standing = standing;
	}
	public LocalDate getLastReferral() {
		return lastReferral;
	}
	public void setLastReferral(LocalDate lastReferral) {
		this.lastReferral = lastReferral;
	}

	public String getUraniumPlusMemberNumber() {
		return uraniumPlusMemberNumber;
	}
	public void setUraniumPlusMemberNumber(String uraniumPlusMemberNumber) {
		this.uraniumPlusMemberNumber = uraniumPlusMemberNumber;
	}

	public LocalDate getUraniumPlusSince() {
		return uraniumPlusSince;
	}

	public void setUraniumPlusSince(LocalDate uraniumPlusSince) {
		this.uraniumPlusSince = uraniumPlusSince;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Customer that))
			return false;
		return Objects.equals(this.id, that.id) && Objects.equals(this.name, that.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.name);
	}

	@Override
	public String toString() {
		return MessageFormat
			.format("Customer'{'id={0}, name={1}, dateOfBirth={2}, smoker={3}, uraniumPlusMemberNumber={4}, uraniumPlusSince={5}'}'",
					this.id,
					this.name,
					this.dateOfBirth,
					this.smoker,
					this.uraniumPlusMemberNumber,
					this.uraniumPlusSince);
	}
}
