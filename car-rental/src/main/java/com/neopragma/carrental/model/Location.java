package com.neopragma.carrental.model;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "location")
public class Location implements Serializable {
	public Location(String language, String country, String state, String city, String iata) {
		this.language = language;
		this.country = country;
		this.state = state;
		this.city = city;
		this.iata = iata;
	}
	public Location() {}

	@JdbcTypeCode(SqlTypes.JSON)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "location")
	Set<BaseDailyRentalRate> baseDailyRentalRateSet;
	private @Id
	@GeneratedValue Long id;
	/** ISO 639 two-character language code */
	private String language;
	/** ISO 3166 two-character country code */
	private String country;
	private String state;
	private String city;
	/** International Air Transport Association airport code */
	private String iata;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getIata() {
		return iata;
	}
	public void setIata(String iata) {
		this.iata = iata;
	}
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Location that))
			return false;
		return Objects.equals(this.id, that.id);
	}
	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.language, this.country);
	}
	@Override
	public String toString() {
		return MessageFormat
			.format("Location '{'id={0}, language={1}, country={2}, state={3}, city={4}, iata={5}'}'",
					this.language,
					this.country,
					this.state,
					this.city,
					this.iata);
	}
}
