package com.neopragma.carrental.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class LocationAndVehicleClassKey implements Serializable {

    public LocationAndVehicleClassKey() {}
    public LocationAndVehicleClassKey(Long locationId, Long vehicleClassId) {
        this.locationId = locationId;
        this.vehicleClassId = vehicleClassId;
    }
    @Column(name = "location_id")
    private Long locationId;
    @Column(name = "vehicleClass_id")
    private Long vehicleClassId;

    public Long getLocationId() {
        return locationId;
    }
    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }
    public Long getVehicleClassId() {
        return vehicleClassId;
    }
    public void setVehicleClassId(Long vehicleClassId) {
        this.vehicleClassId = vehicleClassId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof LocationAndVehicleClassKey that))
            return false;
        return Objects.equals(this.locationId, that.locationId)
                && Objects.equals(this.vehicleClassId, that.vehicleClassId);
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.locationId, this.vehicleClassId);
    }

}
