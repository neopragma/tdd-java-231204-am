package com.neopragma.carrental;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import javax.persistence.Table;
import java.text.MessageFormat;
import java.util.Objects;

@Entity
@Table(name = "vehicle_class")
class VehicleClass {

	private @Id
	@GeneratedValue Long id;
	private String name;
	VehicleClass() {}

	VehicleClass(String name) {

		this.name = name;
	}

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
