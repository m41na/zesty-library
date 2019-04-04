package com.practicaldime.quest.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Location {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	private String city;
	private String state;
	private String zip;
	private String country;
	
	public Location() {
		super();
	}

	public Location(String city, String state, String zip, String country) {
		super();
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.country = country;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
