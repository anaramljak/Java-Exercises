package com.lab6_2.lab6_2.Models;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lab6.lab6.Models.Measurements;

public class Device {
	
	private String id;
	private Client client;
	private Set<Measurements> measurments;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Set<Measurements> getMeasurments() {
		return measurments;
	}
	public void setMeasurments(Set<Measurements> measurments) {
		this.measurments = measurments;
	}
	
	
}
