package com.lab6.lab6.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Measurements {

	@Id
	@GeneratedValue(generator="system-uuid", strategy=GenerationType.AUTO)
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String id;
	private String year;
	private String month;
	private double result;
	
	@ManyToOne
	@JoinColumn(name="device_id")
	@JsonBackReference
	private Device device;
    	
	public Measurements() {
		
	}

	public Measurements(String id, String year, String month, double result, Device device) {
		super();
		this.id = id;
		this.year = year;
		this.month = month;
		this.result = result;
		this.device = device;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public double getResult() {
		return result;
	}


	public void setResult(double result) {
		this.result = result;
	}


	public Device getDevice() {
		return device;
	}


	public void setDevice(Device device) {
		this.device = device;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	
}
