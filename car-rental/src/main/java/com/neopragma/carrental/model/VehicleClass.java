package com.neopragma.carrental.model;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "vehicle_class")
public class VehicleClass implements Serializable {
	public VehicleClass(String name) {

		this.name = name;
	}
	public VehicleClass() {}

	@JdbcTypeCode(SqlTypes.JSON)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "vehicleClass")
	Set<BaseDailyRentalRate> baseDailyRentalRateSet;

	private @Id
	@GeneratedValue Long id;
	String name;
	private String transmissionType;
	private String powerType;

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
	public String getTransmissionType() {
		return transmissionType;
	}
	public void setTransmissionType(String transmissionType) {
		this.transmissionType = transmissionType;
	}
	public String getPowerType() {
		return powerType;
	}
	public void setPowerType(String powerType) {
		this.powerType = powerType;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (!(o instanceof VehicleClass))
			return false;
		VehicleClass that = (VehicleClass) o;
		return Objects.equals(this.id, that.id)
				&& Objects.equals(this.name, that.name)
				&& Objects.equals(this.transmissionType, that.transmissionType)
				&& Objects.equals(this.powerType, that.powerType);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.name, this.transmissionType, this.powerType);
	}

	@Override
	public String toString() {
		return MessageFormat.format("VehicleClass'{'id={0}, name=''{1}'''}'", this.id, this.name);
	}
}
