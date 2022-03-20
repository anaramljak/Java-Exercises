package com.lab6.lab6.Models;

import java.util.UUID;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;


@Entity
public class Address {
	
	@Id
	@GeneratedValue(generator="system-uuid", strategy=GenerationType.AUTO)
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String id;
	private String city;
	private String street;
	private String number;
	@OneToOne(mappedBy="address")
	private Client client;
	
	public Address() {
	
	}
	public Address(String id, String city, String street, String number) {
		this.id = id;
		this.city = city;
		this.street = street;
		this.number = number;
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}

}
