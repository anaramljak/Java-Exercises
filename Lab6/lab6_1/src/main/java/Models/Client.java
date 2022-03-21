package com.lab6.lab6.Models;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Client {

	@Id
	@GeneratedValue(generator="system-uuid", strategy=GenerationType.AUTO)
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String id;
	private String firstName;
	private String secondName;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="address_id")
	private Address address;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="device_id")
	@JsonBackReference
	private Device device;
	
	public Client() {

	}
	
	public Client(String id, String firstName, String secondName, Address address, Device device) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.secondName = secondName;
		this.address = address;
		this.device = device;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

}
