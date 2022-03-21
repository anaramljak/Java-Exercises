package com.lab5.demo.Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lab5.demo.Models.Measurements;
import com.lab5.demo.Repositories.MeasurementRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


@Service
public class MeasurementService {
	@Autowired
	private MeasurementRepository repository;

	public Measurements createMeasurement(Measurements m) {
		repository.add(m);
		return repository.save(m);
		
	}

	public List<Measurements> getMeasurement(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());
		 
        Page<Measurements> pagedResult = repository.findAll(paging);
         
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Measurements>();
        }
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
	
	public Double getMeasurementsByMonths(String year,String month)
	{
		Double count = repository.getMeasurementsByMonths(year,month);
		return count;
	}

}
