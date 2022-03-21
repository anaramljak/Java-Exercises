package com.lab6.lab6.Controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lab6.lab6.ErrorWrapper.DataNotEnteredException;
import com.lab6.lab6.ErrorWrapper.DataNotFoundException;
import com.lab6.lab6.Models.Client;
import com.lab6.lab6.Models.Measurements;
import com.lab6.lab6.Services.ClientService;
import com.lab6.lab6.Services.MeasurementService;
import com.mysql.cj.util.StringUtils;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import org.json.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@RestController
public class MeasurementController<T> {
	@Autowired
	private MeasurementService service;

	@PostMapping("/measurements")
	public ResponseEntity<Measurements> createMeasurement(@RequestBody Measurements m) throws DataNotEnteredException {
		if (m == null)
			throw new DataNotEnteredException("Data for measurements not entered");
		else
			return new ResponseEntity<>(service.createMeasurement(m), HttpStatus.OK);

	}

	@GetMapping("/measure")
	public ResponseEntity<List<Measurements>> getMeasurement(@RequestParam Integer page,
			@RequestParam(defaultValue = "3") Integer pageSize, 
			@RequestParam(defaultValue = "result") String sortBy) throws DataNotFoundException {
		List<Measurements> list = service.getMeasurement(page, pageSize, sortBy);
		if (list == null)
			throw new DataNotFoundException("No measurements exist");
		else
			return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@PutMapping("/measurments/{Id}")
	public ResponseEntity<String> updateMeasurement(@PathVariable(value = "Id") String id,
			@RequestBody Measurements details) throws DataNotFoundException, DataNotEnteredException {
		if (id == null)
			throw new DataNotFoundException("Id is null");
		else if(details == null)
			throw new DataNotEnteredException("Data for measurements not entered");
		else
			return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/measurments/{Id}")
	public ResponseEntity<Measurements> deleteMeasurement(@PathVariable(value = "Id") String id)
			throws DataNotFoundException {
		if (id == null)
			throw new DataNotFoundException("Id is null");
		else {
			service.deleteMeasurement(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	@GetMapping("/measurements/results")
	public String getFilteredResults(@QueryParam("year") String year, @QueryParam("month") String month)
			throws JsonGenerationException, JsonMappingException, IOException {
		HashMap<String, Double> map = new HashMap<String, Double>();
		if (StringUtils.isNullOrEmpty(month)) {
			Double res = service.getAllYearMeasurements(year);
			map.put(year, res);
		}
		if (!StringUtils.isNullOrEmpty(month)) {
			Double res1 = service.getMeasurementsByMonths(year, month);
			map.put(month, res1);
		}

		String json = new ObjectMapper().writeValueAsString(map);
		return json;

	}

	@GetMapping("/yearMeasurments")
	public ResponseEntity<List<String>> geYearMeasurementsByMonths(@QueryParam("year") String year) throws DataNotFoundException {
		List<String> list = service.getYearMeasurementsByMonths(year);
		if (list == null)
			throw new DataNotFoundException("No data exist");
		else
			return new ResponseEntity<>(list, HttpStatus.OK);
	}

}
