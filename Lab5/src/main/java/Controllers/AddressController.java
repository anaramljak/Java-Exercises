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
import org.springframework.web.bind.annotation.RestController;

import com.lab5.demo.Models.Address;
import com.lab5.demo.Services.AddressService;

@RestController
public class AddressController {
	@Autowired
	private AddressService service;

	@PostMapping("/address")
	public ResponseEntity<Address> createAddress(@RequestBody Address a) {
		if (a == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(service.createAddress(a), HttpStatus.OK);

	}

	@GetMapping("/address")
	public ResponseEntity<List<Address>> getAddress() {
		return new ResponseEntity<>(service.getAddress(), HttpStatus.OK);
	}

	@PutMapping("/address/{Id}")
	public ResponseEntity<Address> updateAddress(@PathVariable(value = "Id") String id, @RequestBody Address details) {
		if (details == null || id == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(service.updateAddress(id, details), HttpStatus.OK);
	}

	@DeleteMapping("/address/{Id}")
	public ResponseEntity<Address> deleteAddress(@PathVariable(value = "Id") String id) {
		if (id == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		else {
			service.deleteAddress(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
}
