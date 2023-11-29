package com.neopragma.carrental.model;

import io.hypersistence.utils.hibernate.type.money.MonetaryAmountType;
import jakarta.persistence.*;
import org.hibernate.annotations.CompositeType;

import javax.money.MonetaryAmount;
import java.io.Serializable;
import java.text.MessageFormat;

@Entity
@Table(name = "base_daily_rental_rate")
//@IdClass(LocationAndVehicleClassKey.class)
public class BaseDailyRentalRate implements Serializable {

    public BaseDailyRentalRate() {}
    public BaseDailyRentalRate(
            Long locationId,
            Long vehicleClassId,
            MonetaryAmount baseDailyRate) {
        this.id = new LocationAndVehicleClassKey(locationId, vehicleClassId);
        this.baseDailyRate = baseDailyRate;
    }
    @EmbeddedId
    private LocationAndVehicleClassKey id;

//    @Id
//    private Long locationId;
//    @Id
//    private Long vehicleClassId;

    @AttributeOverrides({
            @AttributeOverride(
                    name = "baseDailyRateAmount",
                    column = @Column(name = "base_daily_rate_amount")
            ),
            @AttributeOverride(
                    name = "baseDailyRateCurrency",
                    column = @Column(name = "base_daily_rate_currency")
            )
    })
    @CompositeType(MonetaryAmountType.class)
    private MonetaryAmount baseDailyRate;

    public LocationAndVehicleClassKey getId() {
        return id;
    }

    public void setId(LocationAndVehicleClassKey id) {
        this.id = id;
    }

    public MonetaryAmount getBaseDailyRate() {
        return baseDailyRate;
    }

    public void setBaseDailyRate(MonetaryAmount baseDailyRate) {
        this.baseDailyRate = baseDailyRate;
    }

    @Override
    public String toString() {
        return MessageFormat
                .format("BaseDailyRentalRate '{'location={0}, vehicleClass={1}, baseDailyRate={2}'}'",
                        this.id.getLocationId(),
                        this.id.getVehicleClassId(),
                        this.baseDailyRate);

    }
}
