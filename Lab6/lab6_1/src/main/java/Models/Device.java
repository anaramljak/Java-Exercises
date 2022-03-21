package com.lab6.lab6.Models;

import java.util.*;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Device {
	
	@Id
	@GeneratedValue(generator="system-uuid", strategy=GenerationType.AUTO)
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String id;
	@OneToMany(mappedBy="device")
	@JsonManagedReference
	private Set<Measurements> measurments;
	@OneToOne(mappedBy="device")
	@JsonManagedReference
	private Client client;
	
	public Device() {
		
	}

	public Device(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public Set<Measurements> getMeasurments() {
		return measurments;
	}

	public void setMeasurments(Set<Measurements> measurments) {
		this.measurments = measurments;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	
}
