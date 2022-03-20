package com.lab5.demo.Controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lab5.demo.Models.Client;
import com.lab5.demo.Models.Measurements;
import com.lab5.demo.Services.ClientService;
import com.lab5.demo.Services.MeasurementService;
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
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@RestController
public class MeasurementController {
	@Autowired
	private MeasurementService service;

	@PostMapping("/measurments")
	public ResponseEntity<Measurements> createMeasurement(@RequestBody Measurements m) {
		if (m == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(service.createMeasurement(m), HttpStatus.OK);
	}

	@GetMapping("/measurments")
	public ResponseEntity<List<Measurements>> getMeasurement(
			@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "3") Integer pageSize, 
			@RequestParam(defaultValue = "result") String sortBy) {
		List<Measurements> list = service.getMeasurement(pageNo, pageSize, sortBy);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@PutMapping("/measurments/{Id}")
	public ResponseEntity<Measurements> updateMeasurement(@PathVariable(value = "Id") String id,
			@RequestBody Measurements details) {
		if (id == null || details == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(service.updateMeasurement(id, details), HttpStatus.OK);
	}

	@DeleteMapping("/measurments/{Id}")
	public ResponseEntity<Measurements> deleteMeasurement(@PathVariable(value = "Id") String id) {
		if (id == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
	public List<String> geYearMeasurementsByMonths(@QueryParam("year") String year) {
		List<String> list = service.getYearMeasurementsByMonths(year);
		return list;
	}

}
