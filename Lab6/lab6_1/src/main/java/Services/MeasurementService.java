package com.lab6.lab6.Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.lab6.lab6.Models.Measurements;
import com.lab6.lab6.Repositories.MeasurementRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
@Component
public class MeasurementService {
	@Autowired
	private MeasurementRepository repository;

	public Measurements createMeasurement(Measurements m) {
		return repository.save(m);
	}

	public List<Measurements> getMeasurement(Integer page,Integer pageSize,String sortBy) {	
		List<Measurements> m = new ArrayList();
		Pageable paging = PageRequest.of(page, pageSize, Sort.by(sortBy).ascending());
		repository.findAll(paging).forEach(m::add);
		return m;
	}

	public void deleteMeasurement(String id) {
		repository.deleteById(id);
	}

	public Measurements updateMeasurement(String id, Measurements details) {
		Measurements m = repository.findById(id).get();
		m.setResult(m.getResult());
		m.setYear(m.getYear());
		m.setMonth(m.getMonth());
		m.setDevice(m.getDevice());
		return repository.save(m);
	}

	public Double getAllYearMeasurements(String year) {
		Double count = repository.getAllYearMeasurements(year);
		return count;

	}

	public List<String> getYearMeasurementsByMonths(String year) {
		List<String> list = repository.getYearMeasurementsByMonths(year);
		return list;

	}

	public Double getMeasurementsByMonths(String year, String month) {
		Double count = repository.getMeasurementsByMonths(year, month);
		return count;
	}

	

}
