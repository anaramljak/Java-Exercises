package com.lab5.demo.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lab5.demo.Models.Client;
import com.lab5.demo.Models.Device;
import com.lab5.demo.Services.ClientService;
import com.lab5.demo.Services.DeviceService;

@RestController
public class DeviceController {
	@Autowired
	private DeviceService service;

	@PostMapping("/device")
	public ResponseEntity<Device> createDevice(@RequestBody Device d) {
		if (d == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(service.createDevice(d), HttpStatus.OK);
	}

	@GetMapping("/device")
	public ResponseEntity<List<Device>> getDevice() {
		return new ResponseEntity<>(service.getDevices(), HttpStatus.OK);
	}

	@PutMapping("/device/{Id}")
	public ResponseEntity<Device> updateDevice(@PathVariable(value = "Id") String id, @RequestBody Device details) {
		if (id == null || details == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(service.updateDevice(id, details), HttpStatus.OK);
	}

	@DeleteMapping("/device/{Id}")
	public ResponseEntity<Device> deleteDevice(@PathVariable(value = "Id") String id) {
		if (id == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		else {
			service.deleteDevice(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
}
