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
	@JdbcTypeCode(SqlTypes.JSON)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "vehicleClass")
	Set<BaseDailyRentalRate> baseDailyRentalRateSet;

	private @Id
	@GeneratedValue Long id;
	private String name;
	public VehicleClass() {}

	public Long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (!(o instanceof VehicleClass))
			return false;
		VehicleClass vehicleClass = (VehicleClass) o;
		return Objects.equals(this.id, vehicleClass.id) && Objects.equals(this.name, vehicleClass.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.name);
	}

	@Override
	public String toString() {
		return MessageFormat.format("VehicleClass'{'id={0}, name=''{1}'''}'", this.id, this.name);
	}
}
