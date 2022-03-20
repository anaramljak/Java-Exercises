package com.lab6.lab6.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lab6.lab6.ErrorWrapper.DataNotEnteredException;
import com.lab6.lab6.ErrorWrapper.DataNotFoundException;
import com.lab6.lab6.Models.Device;
import com.lab6.lab6.Models.Measurements;
import com.lab6.lab6.Services.DeviceService;

@RestController
public class DeviceController {
	@Autowired
	private DeviceService service;

	@PostMapping("/device")
	public ResponseEntity<Device> createDevice(@RequestBody Device d) throws DataNotEnteredException {
		if (d == null)
			throw new DataNotEnteredException("Data for device not entered");
		else
			return new ResponseEntity<>(service.createDevice(d), HttpStatus.OK);
	}

	@GetMapping("/device")
	public ResponseEntity<List<Device>> getDevice() {
		return new ResponseEntity<>(service.getDevices(), HttpStatus.OK);
	}

	@PutMapping("/device/{Id}")
	public ResponseEntity<Device> updateDevice(@PathVariable(value = "Id") String id, @RequestBody Device details) throws DataNotFoundException, DataNotEnteredException {
		if (id == null)
			throw new DataNotFoundException("Id is null");
		else if(details == null)
			throw new DataNotEnteredException("Data for device not entered");
		else
			return new ResponseEntity<>(service.updateDevice(id, details), HttpStatus.OK);
	}

	@DeleteMapping("/device/{Id}")
	public ResponseEntity<Device> deleteDevice(@PathVariable(value = "Id") String id) throws DataNotFoundException {
		if (id == null)
			throw new DataNotFoundException("Id is null");
		else {
			service.deleteDevice(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	
	@GetMapping("/device/measurements")
	public ResponseEntity<List<Measurements>> getData(@RequestParam String id,
			@RequestParam(required = false, defaultValue = "0") Integer page,	
			@RequestParam(required = false, defaultValue = "3") Integer pageSize,
			@RequestParam(required = false) String sortBy) throws DataNotFoundException {
		List<Measurements> list = new ArrayList();
		list = service.getData(id,page,pageSize,sortBy);
		if (list == null)
			throw new DataNotFoundException("No measurements for device exist");
		else
			return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
}
